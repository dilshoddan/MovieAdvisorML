
import pandas as pd
import numpy as np
import joblib
import logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)
EMBEDDING_SIZE = 50

class MovieRecommenderSetting:
    def __init__(self):

        logger.info("Starting up the MovieRecommenderSetting: ")

        self.movie_df = pd.read_csv("assets/dataset/movies.csv")
        self.ratingDF = pd.read_csv("assets/dataset/ratings.csv")

    def similarityMatrix(self):

        logger.info("Loading the Neural Network model")
        cosSim = cosine_sim_movies = joblib.load('assets/cosinesim.pkl')
        return cosSim



    

        

    