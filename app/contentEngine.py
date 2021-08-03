# To personalise our recommendations more, I am going to build an engine that computes similarity between
# movies based on certain metrics and suggests movies that are most similar to a particular movie that a user liked.
# Since we will be using movie metadata (or content) to build this engine, this also known as Content Based Filtering.

# I will build two Content Based Recommenders based on:
# genre

# import packages
from math import sqrt
import pandas as pd
import numpy as np
import joblib
import logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

class ContentBased:
    def __init__(self, moviesDF, ratingDF, cosine_sim):
        # logger.info("Initializing Content Based Engine....")
        self.moviesDF = moviesDF
        self.ratingDF = ratingDF
        # tfidf_movies_genres = TfidfVectorizer(token_pattern = '[a-zA-Z0-9\-]+')
        # tfidf_movies_genres_matrix = tfidf_movies_genres.fit_transform(self.moviesDF['genres'])
        # tfidf = joblib.load('assets/tfidf.pkl')
        self.cosine_sim_movies = cosine_sim
        # self.cosine_sim_movies = linear_kernel(tfidf, tfidf)

    def get_recommendations_based_on_genres(self, movie_title, byTitle=True):
        """
        Calculates top 2 movies to recommend based on given movie titles genres. 
        :param movie_title: title of movie to be taken for base of recommendation
        :param cosine_sim_movies: cosine similarity between movies 
        :return: Titles of movies recommended to user
        """
        # logger.info("Retrieving Recommendation based on movie title....")
        # Get the index of the movie that matches the title
        idx_movie = self.moviesDF.loc[self.moviesDF['title'].isin([movie_title])]
        idx_movie = idx_movie.index
        
        # Get the pairwsie similarity scores of all movies with that movie
        sim_scores_movies = list(enumerate(self.cosine_sim_movies[idx_movie][0]))
        
        # Sort the movies based on the similarity scores
        sim_scores_movies = sorted(sim_scores_movies, key=lambda x: x[1], reverse=True)

        # Get the scores of the 10 most similar movies
        sim_scores_movies = sim_scores_movies[1:11]
        
        # Get the movie indices
        movie_indices = [i[0] for i in sim_scores_movies]
        
        # Return the top 2 most similar movies
        recommended_movies =  self.moviesDF[['movieId','title','genres']].iloc[movie_indices]
        recomList = []
        
        for row in recommended_movies.itertuples():
            recomList.append({"movieId" : row.movieId,"title" : row.title,"genre" : row.genres})
        
        return recomList

    # get_recommendations_based_on_genres("Father of the Bride Part II (1995)")

    def get_recommendation_content_model(self, userId):
        """
        Calculates top movies to be recommended to user based on movie user has watched.  
        :param userId: userid of user
        :return: Titles of movies recommended to user
        """
        # logger.info("Retrieving Recommendation based on movie userId....")
        recommended_movie_list = []
        movie_list = []
        moviesbyGenres = []
        df_rating_filtered = self.ratingDF[self.ratingDF["userId"]== userId]
        for key, row in df_rating_filtered.iterrows():
            movie_list.append((self.moviesDF["title"][row["movieId"]==self.moviesDF["movieId"]]).values) 
        
        small_dfs = []
        for index, movie in enumerate(movie_list):
            for small_df in self.get_recommendations_based_on_genres(movie[0]):
                moviesbyGenres.append(small_df)
 
        df = pd.DataFrame(moviesbyGenres)
        
        joined = pd.merge(df, df_rating_filtered, on=['movieId'], how='left')
        joined = joined.head(10)
        # logger.info(joined)
        recomList = []
        for row in joined.itertuples():
            recomList.append({"movieId" : row.movieId,"title" : row.title,"genre" : row.genre})
        
        return recomList
    

