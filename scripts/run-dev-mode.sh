#!/bin/bash

./mvnw compile quarkus:dev -pl "$1" -DskipTests