### How to build & run

* Clean Project
```
mvn clean
```

* Build Project while skipping Unit Tests
```
mvn install -DskipTests 
```

* Run Unit Tests
```
mvn test
```
**Note:** Unit Test coverage report will be generated inside target/site/jacoco -> **index.html** file.

* Build Project while running Unit Tests
```
mvn install
```

* Build an executable jar
```
mvn package
```

* Run Project
```
mvn spring-boot:run
```

## Notes

* Postman Collection
```
Shop Apotheke GitHub Discovery Service.postman_collection.json
```

> **Create Customer API:** Creates a new Customer.

* Actuator Health
> [Health Page](http://localhost:8099/actuator/health)
