# NetFreaks (video renting)

## Introduction

This is a (_really_) simple video renting, with a _REST-ish_ API, made in Java. The back-end uses an [H2 database](http://www.h2database.com/html/main.html) behind to store the customers, films and rentals. Moreover, [Hibernate](http://hibernate.org/) is used to communicate with the database. The _seed_ data is available in the [seed](https://github.com/joaoqalves/netfreaks/blob/master/src/main/java/net/joaoqalves/Seed.java) file.

## Design Assumptions

+ One film may have several copies that can be rented
+ Only one currency, but the design would allow more
+ No price/surcharge history in the database
+ No timezone support
+ No focus on Unit tests
+ No logging
+ No concurrency handling when getting the films (what happens if the last copy was rented just before?)


### Available resources

The following resources are available:

+ ``GET /customers``
+ ``GET /customers/{id}``
+ ``POST /customers/{id}/rent``, to rent one or more films. The payload should be a JSON list that contains elements of [NewRental](https://github.com/joaoqalves/netfreaks/blob/master/src/main/java/net/joaoqalves/domain/rental/NewRental.java)
+ ``POST /customers/{id}/return``, to return one or more films. The payload should be a JSON list that contains elements of [NewReturn](https://github.com/joaoqalves/netfreaks/blob/master/src/main/java/net/joaoqalves/domain/returnfilm/NewReturn.java)
+ ``GET /films``
+ ``GET /films/{id}``
+ ``GET /rentals``
+ ``GET /rentals/{id}``

### Further considerations

In this demo instance, the database is restored each 10 minutes, through a ``cron`` job.

## How to build and execute it

### Requirements

+ git
+ Java 8
+ Maven 3

Just clone this project and execute ``mvn package``. Then, go to the ``target``folder and execute ``java -jar film-rental-1.0-SNAPSHOT.jar server``.

## Trying out the ReST API

If you want to try out the ReST API just use the above end-points or you can run the ``api_test.py`` script that is present in the root of this repository. Just install the dependencies with ``pip install -r requirements.txt`` and then execute ``python api_test.py`` and you should be good to go :)

