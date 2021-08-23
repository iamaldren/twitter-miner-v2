# cloud-stream-demo

The main purpose of this mini project is for me to explore Spring Cloud Stream, as I will be using it in my current work. So I created my own requirement/problem, as this will help me to learn properly.

This is the java version of the twitter-miner that I created in Python last time.

## Tech Stacks
- Spring Boot
- Spring Cloud Stream
- Spring Social
- Spring Data
- Apache Kafka
- StanfordCoreNLP
- Elasticsearch
- Kibana
- Redis

## What it is?

Basically, this mini project would mine tweets, based on keyword used, from Twitter. The tweets will then get analyzed for its polarity and identify its sentiment, and these processed tweets would then get ingested to Elasticsearch.

The project is divided into 3 modules. The `source`, `processor`, and `sink`.

### miner-source

This module will be responsible for integrating with Twitter API to mine the tweets, afterwhich it will pass the data down to the `processor` service for sentiment analysis.

As of the moment, the keyword needs to get defined in the application properties file. But it can be improved, like exposing a REST endpoint to input the keyword, and trigger the stream from there.

### miner-processor

This module will consume data from `source` service; it will then analyze the tweet by using the `StanfordCoreNLP` library, this library would be responsible for identifying the tweet's sentiment. After processing, the data will then be streamed down to the `sink` service.

This module could still be improved in terms of processing the data to get a more fine grained results that can be use for data analysis.

Click [here](https://nlp.stanford.edu/sentiment/) to understand how the `StanfordCoreNLP` library works.

#### What were considered?
- There will be threshold for including the tweet from a specific user. The reason for this is we don't want to spam the data coming from the same user. If we really want to identify the sentiment for a specific topic, it would be better if we collect data from different users.
- Retweets will be part of the processing, since a retweet is basically a tweet (albeit not his own) coming from a different user.

#### Things to ponder?
- How can we be sure that the hashtag (keyword) is really related to the tweet?

### miner-sink

This module will consume sentiment data, and will ingest to Elasticsearch. As of the moment, this only supports 1 ES index which is `twitter`. It can be improved to dynamically create/update the index which the data is related too.

## StanfordCoreNLP Sentiment Point System
- VERY_POSITIVE = 4
- POSITIVE = 3
- NEUTRAL = 2
- NEGATIVE = 1
- VERY_NEGATIVE = 0

## Running the program

Do note that this project will be using all default configurations. Just update the configs based on your needs if you want to run this program.

### Prerequisites
- Java 11
- Maven
- Kafka
- Zookeeper
- Redis
- Elasticsearch
- Kibana (Optional)

This project includes a docker-compose file that contains all the needed services to run the project, excluding Java and Maven (of course). 

If you don't have the services installed, you can run the docker-compose file, by executing the command below (this goes without saying that you must have docker running in your machine):
```shell
$ docker-compose up -d
```
Make sure that you are in the directory where the `docker-compose` file is located.

### Run via Maven spring-boot plugin

You can run the program by executing the command below. Command must be executed in each module directory where the `pom.xml` file is located.
```shell
$ mvn spring-boot:run
```

### Kibana

You can aggregate the data in Kibana for your analysis.

## What to improve
- Use Spring Reactive
- Improve Data Model generation
- Unit tests
- Keyword input