#!/bin/bash

./mvnw clean package -pl "$1" -Pnative -DskipTests

docker build -f "$1"/src/main/docker/Dockerfile.native -t kkottke/"$1" .