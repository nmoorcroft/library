# AngularJS & Dropwizard Library Demo

This is a demo application built using Dropwizard and AngularJS.

## Prerequisites

The project must be built using Apache Maven

    mvn clean package

Jasmine JavaScript tests require Karma and PhantomJS

    npm install -g karma
    npn install -g phantomjs

For Karma to start PhantomJS the env var PHANTOMJS_BIN must point to the PhantomJS executable

    set PHANTOMJS_BIN = C:\Users\xxx\AppData\Roaming\npm\node_modules\phantomjs\lib\phantom\phantomjs.exe

## Running the demo

Before starting the server for the first time the database must be setup, from the root of the project run

    java -jar target\library-0.0.1-SNAPSHOT.jar db migrate library.yml

and start the server with

    java -jar target\library-0.0.1-SNAPSHOT.jar server library.yml


## Line endings

Karma's html2js will fail if any partials have windows line ending, convert line endings to unix.



