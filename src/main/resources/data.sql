--CREATE TABLE IF NOT EXISTS product(
--    `id` bigint(20) NOT NULL AUTO_INCREMENT,
--    `image_url` varchar(255) DEFAULT NULL,
--    `name` varchar(255) DEFAULT NULL,
--    PRIMARY KEY (`id`)
--)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
--DELETE FROM product;
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/23420/image/1462221519/600.jpg', 'CREMEFRAICHE');
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/213942/image/1560262700/600.jpg', 'PEPSI MAX');
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/201003/image/1553075410/600.jpg', 'CARLSBERG');
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/400176/image/1569941273/600.jpg', 'HK. OKSEKØD 15-18');
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/212652/image/1549284118/600.jpg', 'COCA COLA');
--INSERT INTO product(image_url, name) VALUES ('https://cphapp.rema1000.dk/api/v1/catalog/store/1/item/61250/image/1534406146/600.jpg', 'BURGERBOLLER');

----
---- Dumping data for table `user`
----
--
--LOCK TABLES `user` WRITE;
--/*!40000 ALTER TABLE `user` DISABLE KEYS */;
--INSERT INTO `user` VALUES (1,'test','ROLE_USER','MihaVrhunc'),(2,'test','ROLE_USER','johnnash'),(3,'Test','ROLE_USER','Test'),(4,'test','ROLE_USER','joesiegal'),(5,'test','ROLE_USER','user'),(6,'test','ROLE_ADMIN','admin'),(7,'test','ROLE_USER','johnsmith'),(8,'test','ROLE_USER','mike');
--/*!40000 ALTER TABLE `user` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `genre`
----
--
--LOCK TABLES `genre` WRITE;
--/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
--INSERT INTO `genre` VALUES (1,'Drama'),(2,'Crime'),(3,'Thriller'),(4,'Comedy'),(5,'Action'),(6,'Romance'),(7,'Animation'),(8,'Adventure'),(9,'Fantasy');
--/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `star`
----
--
--LOCK TABLES `star` WRITE;
--/*!40000 ALTER TABLE `star` DISABLE KEYS */;
--INSERT INTO `star` VALUES (1,'Joaquin Phoenix'),(2,'Robert De Niro'),(3,'Zazie Beetz'),(4,'Tim Robbins'),(5,'Morgan Freeman'),(6,'Bob Gunton'),(7,'Marlon Brondo'),(8,'Al Pacino'),(9,'James Caan'),(10,'Robert Duvall'),(11,'Christian Bale'),(12,'Heath Ledger'),(13,'Aaron Eckhart'),(14,'Roberto Benigni'),(15,'Nicoletta Braschi'),(16,'Giorgio Cantarini'),(17,'Kang-ho Song'),(18,'Sun-kyun Lee'),(19,'Yeo-jeong Cho'),(20,'Tom Hanks'),(21,'Tim Allen'),(22,'Don Rickles'),(23,'Anthony Hopkins'),(24,'Olivia Colman'),(25,'Mark Gatiss'),(26,'Tom Hiddleston'),(27,'Owen Wilson'),(28,'Gugu Mbatha-Raw');
--/*!40000 ALTER TABLE `star` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `movie`
----
--
--LOCK TABLES `movie` WRITE;
--/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
--INSERT INTO `movie` VALUES (4,'In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.',1023267,8.4,'https://m.media-amazon.com/images/I/91F6aF4UJ0L._AC_SL1500_.jpg',9.7,'Joker','2021-07-13 16:13:18.312196'),(5,'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',2419048,9.3,'https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_QL75_UX190_CR0,0,190,281_.jpg',10,'The Shawshank Redemption','1994-07-14 11:37:07.409912'),(6,'An organized crime dynasty\'s aging patriarch transfers control of his clandestine empire to his reluctant son.',1673008,9.2,'https://images-na.ssl-images-amazon.com/images/I/81ijayocq7L._AC_SY606_.jpg',9.2,'The Godfather','2010-07-14 11:44:53.995410'),(7,'The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.',1161904,9,'https://images-na.ssl-images-amazon.com/images/I/41N2vp6wUiL._AC_.jpg',9,'The Godfather: Part II','2012-07-14 11:48:48.202014'),(8,'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',2376417,9,'https://m.media-amazon.com/images/I/61zBUhQj22L._AC_SY679_.jpg',9,'The Dark Knight','2019-07-14 11:53:46.300732'),(9,'When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp.',641256,8.6,'https://images-na.ssl-images-amazon.com/images/I/5123rLYbyXL._AC_.jpg',10,'Life Is Beautiful','2001-07-14 12:00:31.347811'),(10,'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.',628461,8.6,'https://images-na.ssl-images-amazon.com/images/I/91sustfojBL._AC_SY679_.jpg',8,'Parasite','2021-07-14 12:05:01.808904'),(11,'A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy\'s room.',911726,8.3,'https://m.media-amazon.com/images/I/51K8r9COEQL._AC_.jpg',9,'Toy Story','2005-07-14 12:10:17.204931'),(12,'A man refuses all assistance from his daughter as he ages. As he tries to make sense of his changing circumstances, he begins to doubt his loved ones, his own mind and even the fabric of his reality.',78059,8.3,'https://m.media-amazon.com/images/M/MV5BZWQ3MjBjN2MtMjIxYS00MjBlLWFmYTktN2RlZmVkMWUzYTYwXkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg',10,'The Father','2021-07-14 12:16:10.043276');
--/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `review`
----
--
--LOCK TABLES `review` WRITE;
--/*!40000 ALTER TABLE `review` DISABLE KEYS */;
--INSERT INTO `review` VALUES (1,'Outstanding movie with a haunting performance and best character development ever seen','2019-10-03 00:00:00.000000',1),(3,'Joaquin \'OSCAR\', Joker = best Dark suspense thriller ... Darker than dark Knight.','2021-07-14 11:28:08.018373',2),(4,'cool movie!!!','2021-07-14 13:45:13.996135',2),(5,'A black comedy on social inequality','2021-07-14 13:52:35.863805',4),(6,'cool movie!!!','2021-07-14 14:17:17.082910',5),(7,'Cartoon from my childhood','2021-07-15 10:44:04.011889',3),(8,'I liked the movie very much.','2021-07-15 10:48:19.800133',2),(9,'cool!','2021-07-15 11:21:03.061105',5),(10,'nice movie!','2021-07-15 11:21:42.849074',7),(11,'I like this movie!','2021-07-15 12:30:35.647655',8),(12,'I liked the movie very much.','2021-07-15 12:40:11.033338',2);
--/*!40000 ALTER TABLE `review` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `movie_genre`
----
--
--LOCK TABLES `movie_genre` WRITE;
--/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
--INSERT INTO `movie_genre` VALUES (4,1),(4,2),(4,3),(6,2),(6,1),(7,2),(7,1),(8,5),(8,2),(8,1),(9,4),(9,1),(9,6),(10,4),(10,1),(10,3),(11,7),(11,8),(11,4),(12,1),(5,1),(5,2),(5,3);
--/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `vote`
----
--
--LOCK TABLES `vote` WRITE;
--/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
--INSERT INTO `vote` VALUES (1,9,1),(3,10,2),(4,10,2),(5,8,4),(6,10,5),(7,9,3),(8,10,2),(9,9,5),(10,9,7),(11,10,8),(12,10,2);
--/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `movie_vote`
----
--
--LOCK TABLES `movie_vote` WRITE;
--/*!40000 ALTER TABLE `movie_vote` DISABLE KEYS */;
--INSERT INTO `movie_vote` VALUES (4,1),(4,3),(4,6),(5,8),(7,9),(7,10),(9,12),(10,5),(11,7),(12,4),(12,11);
--/*!40000 ALTER TABLE `movie_vote` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `movie_star`
----
--
--LOCK TABLES `movie_star` WRITE;
--/*!40000 ALTER TABLE `movie_star` DISABLE KEYS */;
--INSERT INTO `movie_star` VALUES (4,1),(4,2),(4,3),(6,7),(6,8),(6,9),(7,8),(7,2),(7,10),(8,11),(8,12),(8,13),(9,14),(9,15),(9,16),(10,17),(10,18),(10,19),(11,20),(11,21),(11,22),(12,23),(12,24),(12,25),(5,4),(5,5),(5,6);
--/*!40000 ALTER TABLE `movie_star` ENABLE KEYS */;
--UNLOCK TABLES;
--
----
---- Dumping data for table `movie_review`
----
--
--LOCK TABLES `movie_review` WRITE;
--/*!40000 ALTER TABLE `movie_review` DISABLE KEYS */;
--INSERT INTO `movie_review` VALUES (4,1),(4,3),(4,6),(5,8),(7,9),(7,10),(9,12),(10,5),(11,7),(12,4),(12,11);
--/*!40000 ALTER TABLE `movie_review` ENABLE KEYS */;
--UNLOCK TABLES;
--
--
--
