# Atse Wildlife Tracker

## Description 
This is application that allows Rangers to track wildlife sightings in the area.

## Author
* Abel B.

## Technologies
1. Java
2. Spark
3. Handlebars
4. Sql
5. Sql2o
6. PostgreSQL
7. Bootstrap
8. CSS3

## Setup/Instructions
#### Foolish assumptions
* You have PostgreSql and JDK 8+ installed
* You have a idea (preferred Intellij)

1. Clone the repo ``` https://github.com/Abel-b/Atse-Wild```
2. Open the folder from github in your idea
1. On your terminal type ```psql``` to open postgresql command shell
2. Create database ``` CREATE DATABASE wildlife_tracker```
3. Connect to the DB ``` \c wildlife_tracker```
4. Create table ``` CREATE TABLE animals(id serial PRIAMRY KEY,name varchar, endangered boolean, health int, age int)```
*``` CREATE TABLE sightings( id serial PRIMARY KEY,rangerName varchar, location varchar, animalid int)```

### Contact
Github [vicky_teka](https://github.com/Abel-b)

### LICENSE
MIT License [![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)


&copy; 2019 Abel-B