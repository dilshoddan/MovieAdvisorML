package com.movieadvisor.controller;

import com.movieadvisor.model.Comment;
import com.movieadvisor.model.Movie;
import com.movieadvisor.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("fiveNewMovies",
                movieService.getAllNewMovies().stream().limit(5).collect(Collectors.toList()));
        model.addAttribute("fiveTopMovies",
                movieService.getAllTopMovies().stream().limit(5).collect(Collectors.toList()));
        model.addAttribute("greeting", "Hello");
        return "index";
    }

    @GetMapping("newmovies")
    public String newMovies(Model model) {
        model.addAttribute("page", "newmovies");
        model.addAttribute("movies", movieService.getAllNewMovies());
        return "movies";
    }

    @GetMapping("top10movies")
    public String top10Movies(Model model) {
        model.addAttribute("page", "top10movies");
        model.addAttribute("movies", movieService.getTop10Movies());
        return "movies";
    }

    @GetMapping("becauseyouwatched")
    public String becauseYouWatched(Model model, HttpSession session) {
        String username = (String)session.getAttribute("username");
        model.addAttribute("page", "becauseyouwatched");
        model.addAttribute("movies", movieService.becauseYouWatched(username));
        return "movies";
    }

    @GetMapping("foryou")
    public String forYou(Model model, HttpSession session) {
        String username = (String)session.getAttribute("username");
        model.addAttribute("page", "foryou");
        model.addAttribute("movies", movieService.forYou(username));
        return "movies";
    }

    @GetMapping("foryou2")
    public String forYou2(Model model, HttpSession session) {
        String username = (String)session.getAttribute("username");
        model.addAttribute("page", "foryou2");
        model.addAttribute("movies", movieService.forYou2(username));
        return "movies";
    }

    @GetMapping("allmovies")
    public String allMovies(Model model) {
        model.addAttribute("page", "allmovies");
        model.addAttribute("movies", movieService.getAllTopMovies());
        return "movies";
    }

    @GetMapping("recalculateratings")
    public void reCalculateRatings() {
        movieService.reCalculateRatings();

    }

    @GetMapping("imdbmovies")
    public String imdbMovies(Model model) {
        model.addAttribute("page", "imdbmovies");
        model.addAttribute("movies", movieService.getAllIMDbMovies());
        return "movies";
    }

    @GetMapping("genres")
    public String genres() {
        return "redirect:genres/Drama";
    }

    @GetMapping("genres/{genre}")
    public String genre(@PathVariable("genre") String genre, Model model) {
        model.addAttribute("genres", movieService.getAllGenres());
        model.addAttribute("movies",
                movieService.getMoviesByGenre(genre));
        model.addAttribute("page", genre);
        return "genres";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("movie/{id}")
    public String movie(@PathVariable("id") Long id, @RequestParam(value = "rate", required = false) Integer rate, Model model, HttpSession session) {
        String username = (String)session.getAttribute("username");
        model.addAttribute("username", username);
        Comment comment = new Comment();
        comment.setVote(rate == null ? 0 : rate.intValue());
        if(username != null) model.addAttribute("comment", comment);
        Movie movie = movieService.getMovie(id);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", movie.getReviews());
        model.addAttribute("rate", rate == null ? 0 : rate.intValue());
        return "movie";
    }

    @PostMapping("movie/{id}")
    public String addComment(@PathVariable("id") Long movieId, @ModelAttribute Comment comment, Model model, HttpSession session) {
        String username = (String)session.getAttribute("username");
        double rating = 0; String comm = null;
        if (comment.getVote() > 0)
            rating = movieService.voteForMovie(movieId, username, comment.getVote());
        if (comment.getComment() != null)
            comm = movieService.reviewMovie(movieId, username, comment.getComment());
        return "redirect:/movie/"+ movieId.toString()+"?rate="+comment.getVote();
    }
}
