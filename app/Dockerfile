FROM amancevice/pandas:1.3.1-jupyter
COPY . /app
WORKDIR /app
#USER root
#RUN apk update
#RUN apk add python3
#RUN apk add py3-setuptools
#RUN apk add gcc musl-dev python3-dev libffi-dev openssl-dev
RUN pip install --upgrade pip
#RUN pip install Flask
#RUN pip install flask_restful
#RUN pip install mysql-connector-python
#RUN pip install numpy
#RUN pip install pandas
#RUN pip install surprise
RUN pip install -r requirements.txt
#EXPOSE 5000
#ENTRYPOINT ["python"]
CMD ["python", "app.py"]