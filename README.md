# Prime Number Finder
![Error](/src/main/resources/static/img/main.png?raw=true "Main")

This is a Spring Boot, Java, Maven application to find prime numbers in an interval (inclusive).\
User can select between a brute force method and an optimized method


## Info
Clone repo and run application
```bash
./mvnw spring-boot:run
```
```bash
Java Version -- openjdk version "17.0.5" 2022-10-18\
```
PrimeNumberGenerator.java path:
```bash
src/main/java/com/evercommerce/updox/prime/PrimeNumberGenerator.java
```

## Testing
Tests can be found here:
```bash
src/test/java/com/evercommerce/updox/prime/PrimeApplicationTests.java
```
Run using
```bash
./mvnw test
```

To test coverage Jacoco is used.
```bash
./mvnw jacoco:prepare-agent test install jacoco:report
```

To find interactive report of test coverage go to index.html below after creating the report with the above command
```bash
target/site/jacoco/index.html
```

Test snapshots below\
![Error](/src/main/resources/static/img/coverage1.png?raw=true "Coverage 1")
![Error](/src/main/resources/static/img/coverage2.png?raw=true "Coverage 2")