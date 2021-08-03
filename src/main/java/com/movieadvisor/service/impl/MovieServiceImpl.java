package com.movieadvisor.service.impl;

import com.movieadvisor.exception.MovieNotFoundException;
import com.movieadvisor.exception.UserNotFoundException;
import com.movieadvisor.model.*;
import com.movieadvisor.repository.*;
import com.movieadvisor.model.*;
import com.movieadvisor.service.MovieService;
import com.movieadvisor.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StarRepository starRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie getMovie(Long id) {
        Optional<Movie> m = movieRepository.findById(id);
        return m.orElseThrow(MovieNotFoundException::new);
    }

    public List<Movie> becauseYouWatched(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        RestTemplate restTemplate = new RestTemplate();
        final String uri = "http://app/recommendById/"+user.getUserId();
        System.out.println(uri);

        MovieItem2[] result = restTemplate.getForObject(uri, MovieItem2[].class);
        List<Movie> apiMovies = new ArrayList<>();
        for (MovieItem2 item:result) {
            Movie m = getMovie(item.getMovieId());
            System.out.println(item.getMovieId());
//            System.out.println(item);
            apiMovies.add(m);
        }

        return apiMovies.stream().filter(apiMovies::contains).sorted(
                (o1, o2) -> (int) (100*(o2.getRating() - o1.getRating()))
        ).limit(10).collect(Collectors.toList());
    }

    public List<Movie> forYou2(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
//        System.out.println(user.getUserId());
        //312
        request.put("userid", user.getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        final String uri = "https://movieadvisor-api.herokuapp.com/nnrecommend";
        MovieItem[] result = restTemplate.postForObject(uri, entity, MovieItem[].class);
        List<Movie> apiMovies = new ArrayList<>();
        for (MovieItem item:result) {
            Movie m = getMovie(item.getMovieID());
//            System.out.println(item.getMovieID());
            apiMovies.add(m);
        }

        return apiMovies.stream().filter(apiMovies::contains).sorted(
                (o1, o2) -> (int) (100*(o2.getRating() - o1.getRating()))
        ).limit(10).collect(Collectors.toList());
    }

    public List<Movie> forYou(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
//        System.out.println(user.getUserId());
        //312
        request.put("userid", user.getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        final String uri = "https://movieadvisor-api.herokuapp.com/svdrecommend";
        MovieItem[] result = restTemplate.postForObject(uri, entity, MovieItem[].class);
        List<Movie> apiMovies = new ArrayList<>();
        for (MovieItem item:result) {
            Movie m = getMovie(item.getMovieID());
//            System.out.println(item.getMovieID());
            apiMovies.add(m);
        }

        return apiMovies.stream().filter(apiMovies::contains).sorted(
                (o1, o2) -> (int) (100*(o2.getRating() - o1.getRating()))
        ).limit(10).collect(Collectors.toList());
    }

    public Movie saveMovie(Movie movie) {
        List<Genre> genres = movie.getGenres();
        movie.setGenres(null);
        for (int i = 0; i < genres.size(); i++)
        {
            Optional<Genre> g = genreRepository.findOneByNameIgnoreCase(genres.get(i).getName());
            if (!g.isPresent()) {
                genres.set(i, genreRepository.save(new Genre(genres.get(i).getName())));
            } else {
                genres.set(i, g.get());
            }
        }
        movie.setGenres(genres);
        List<Star> stars = movie.getMovieStars();
        movie.setMovieStars(null);
        for (int i = 0; i < stars.size(); i++)
        {
            Optional<Star> s = starRepository.findOneByNameIgnoreCase(stars.get(i).getName());
            if (!s.isPresent()) {
                stars.set(i, starRepository.save(stars.get(i)));
            } else {
                stars.set(i, s.get());
            }
        }
        movie.setMovieStars(stars);
        return movieRepository.save(movie);
    }

    public double voteForMovie(Long movieId, String username, int rating) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Movie movie = getMovie(movieId);
        List<Vote> votes = voteRepository.findAllByMovie(movie);
        if (votes.stream().anyMatch(vote -> vote.getUser().getUsername().equals(username)))
            votes.stream().filter(vote -> vote.getUser().getUsername().equals(username))
                .forEach(vote -> vote.setNoOfstars(rating));
        else {
            Vote vote = new Vote(rating, user, movie);
            vote = voteRepository.save(vote);
            votes.add(vote);
        }
        double average = votes.stream().mapToInt(vote ->vote.getNoOfstars()).average().getAsDouble();
        movie.setRating(Math.round(10 * average)/10.0);
        movieRepository.save(movie);
        return movie.getRating();
    }

    public void reCalculateRatings() {
        movieRepository.findAll().forEach(movie -> {
            List<Vote> votes = voteRepository.findAllByMovie(movie);
            if (votes.stream().mapToInt(vote ->vote.getNoOfstars()).average().isPresent()) {
                double average = votes.stream().mapToInt(vote -> vote.getNoOfstars()).average().getAsDouble();
                movie.setRating(Math.round(10 * average) / 10.0);
                movieRepository.save(movie);
            }
        });

    }

    @Override
    public String reviewMovie(Long movieId, String username, String comment) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Movie movie = getMovie(movieId);
        Review review = new Review(comment, user, movie);
        review = reviewRepository.save(review);
        return new StringJoiner("\n")
                .add("User commented:")
                .add(user.getUsername() + ": " + comment)
                .toString();
    }

    public Movie updateMovie(Movie theMovie) {
        Movie movie = getMovie(theMovie.getMovieId());
        if (theMovie.getTitle() != null) movie.setTitle(theMovie.getTitle());
        if (theMovie.getDescription() != null) movie.setDescription(theMovie.getDescription());
        if (theMovie.getImdbRate() > 0) movie.setImdbRate(theMovie.getImdbRate());
        if (theMovie.getImdbCount() > 0) movie.setImdbCount(theMovie.getImdbCount());
        if (theMovie.getGenres() != null && !theMovie.getGenres().isEmpty()) {
            movie.setGenres(theMovie.getGenres());
        }
        if (theMovie.getMovieStars() != null && !theMovie.getMovieStars().isEmpty()) {
            movie.setMovieStars(theMovie.getMovieStars());
        }
        if (theMovie.getReviews() != null && !theMovie.getReviews().isEmpty()) movie.setReviews(theMovie.getReviews());
        if (theMovie.getRating() > 0) movie.setRating(theMovie.getRating());
        return this.saveMovie(movie);
    }

    public boolean deleteMovieById(Long movieId) {
        movieRepository.deleteByMovieId(movieId);
        return true;
    }
/*
    public boolean deleteComment(Long id, String username) {
        Movie m = getMovie(id);
        if(m == null) return false;
        for(String user : m.getComments().keySet()) {
            if(user.equals(username)) {
                m.getComments().remove(user);
                movieRepository.save(m);
                return true;
            }
        }
        return false;
    }
*/
    public List<Movie> getAllNewMovies() {
        return getAllMovies().stream()
                .filter(Movie::isNew)
                .sorted((m1, m2) -> m2.getUpdatedAt().compareTo(m1.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public List<Movie> getAllTopMovies() {
        return getAllMovies().stream().sorted(
                (o1, o2) -> (int) (100*(o2.getRating() - o1.getRating()))
        ).collect(Collectors.toList());
    }

    public List<Movie> getTop10Movies() {
        return getAllMovies().stream().sorted(
                (o1, o2) -> (int) (100*(o2.getRating() - o1.getRating()))
        ).limit(10).collect(Collectors.toList());
    }

    public List<Movie> getAllIMDbMovies() {
        return getAllMovies().stream().sorted(
                (o1, o2) -> (int) (100*(o2.getImdbRate() - o1.getImdbRate()))
        ).collect(Collectors.toList());
    }

    public List<Movie> getMoviesByGenre(String genre) {
        Genre g = genreRepository.findOneByNameIgnoreCase(genre).orElse(null);
        return getAllMovies().stream()
                .filter(
                        movie -> movie.getGenres().contains(g)
                ).collect(Collectors.toList());
    }

    @Override
    public List<String> getComments(Movie movie) {
        return movie
                .getReviews()
                .stream()
                .map(review -> review.getComment())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllGenres() {
        return ((List<Genre>)genreRepository.findAll()).stream().map(Genre::getName).collect(Collectors.toList());
    }
}
