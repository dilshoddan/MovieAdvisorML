
from config import MovieRecommenderSetting
import numpy as np
from flask import Flask, request, jsonify, render_template
from flask_restful import Resource, Api

from contentEngine import ContentBased
import logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


app = Flask(__name__)
api = Api(app)

CSV_FILE = 'assets/movieLens.csv'
FILENAME = 'assets/SVDModel.sav'

global mvConfig
mvConfig = MovieRecommenderSetting()
global cbEngine
cbEngine = ContentBased(mvConfig.movie_df, mvConfig.ratingDF, mvConfig.similarityMatrix())
    

@app.route('/')
def home():
   return render_template('index.html')

class Content(Resource):
    # corresponds to the GET request.
    # this function is called whenever there
    # is a GET request for this resource
    def get(self, title):
        result = cbEngine.get_recommendations_based_on_genres(title)
        return jsonify(result)

class ContentRecommender(Resource):
  
    def get(self, num):
        result = cbEngine.get_recommendation_content_model(num)
        return jsonify(result)


# api.add_resource(Home, '/')
api.add_resource(Content, '/content/<title>')
api.add_resource(ContentRecommender, '/recommendById/<int:num>')


if __name__ == "__main__":
    logger.info("Starting up the Movie Recommender API: ")
    app.run(host='0.0.0.0',  port=80, debug=True)
