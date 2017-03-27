# scala-solr-client
This repository contains the basic starter app with update and search operations for solr With Scala

## Prerequisites:

Solr must have been installed on your system and running on port : 8983. If you have not downloaded it See the link [here](http://www-eu.apache.org/dist/lucene/solr/)
After Downloading Solr use these commands which will create collection and indexes for data :

1. Start Solr : 

    `<base_dir>/bin/solr start -e cloud -noprompt`

2. Create Index for Json and load data : 

    `<base_dir>/bin/post -c gettingstarted example/exampledocs/books.json`

## How to run this :

There are two ways to run this project 
  
    mvn clean compile package
    
    mvn clean compile test

This will fire up the test cases for the solr client
