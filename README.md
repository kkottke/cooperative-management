## Building native executable

```
./mvnw clean package -pl employee-management -Pnative
```

## Running native docker image

* Create native docker image:
```
docker build -f employee-management/src/main/docker/Dockerfile.native -t kkottke/employee-management .
```

```
docker run --rm -p 8080:8080 kkottke/employee-management
```

* Running native docker image using docker-compose:
```
docker-compose -f src/main/docker/docker-compose.yml up -d
```

* Stopping:
```
docker-compose -f src/main/docker/docker-compose.yml down
```