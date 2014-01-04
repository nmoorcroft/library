# AngularJS & Dropwizard Library Demo

## Build

Build the jar with Maven

    mvn clean package

The JavaScript tests require Karma and PhantomJS to be installed

    npm install -g karma
    npm install -g phantomjs

The tests require that PHANTOMJS_BIN points to the PhantomJS executable

    set PHANTOMJS_BIN = C:\Users\xxx\AppData\Roaming\npm\node_modules\phantomjs\lib\phantom\phantomjs.exe

## Running the demo

Setup the local H2 database schema

    java -jar target\library-0.0.1-SNAPSHOT.jar db migrate library.yml

and start the server with

    java -jar target\library-0.0.1-SNAPSHOT.jar server library.yml





