#Hulk Store API

## Goal

An object-oriented solution to the problem is expected. The possibility remains open that the candidate proposes a solution with the restrictions of the functional paradigm.

## statement

**Hulk Store**

The company has decided to carry out a venture, which consists of the creation of a product store for its employees, here you can find t-shirts, glasses, comics, toys and accessories based on Marvel superheroes
and DC comics, including some community-created alternates.
Currently the control is kept in books, with a small kardex system that controls the inventory products, which increase with the entry of new products or decrease with the sale of these. This has worked, but
It has been seen the need to systematize this information in order to make an optimal control and make the service in the store more efficient.

Develop phase I of this project, which consists of creating a kardex system that controls our products, increases with the registration of new products and decreases with the departure of
same, in addition to having the option of registering user accounts and shopping cart. It is important to clarify that when a product does not have stock, it does not allow movements with it.
Clarifications

* Can I use data structures to store the test information?
  You can use any data structure that you consider suitable for development or, failing that, a database
  of data in memory.

* Do I have to work under a previous code architecture?
  No, we want your knowledge of design patterns and good practices to provide us with the best option.

* Can I do it in any language?
  No, this test is to assess knowledge specifically in JAVA and in the front technology of your
  predilection.

* Should I do presentation layer (UI)?
  Yes, basic interfaces for information processing are expected to be developed.

* Do I have to deliver unit tests?
  They are definitely necessary.

* Does the proposed solution have to support high concurrency?
  Definitely yes.

## Guide to the solution

### Run the application

```bash
./gradlew hulk-store-api-products:bootRun
./gradlew hulk-store-api-security:bootRun
./gradlew hulk-store-api-shopping-cart:bootRun
```

Swagger provides a web interface to the API.

* [API Products - Swagger Documentation](http://localhost:3001/products/swagger-ui/index.html)
* [API Security - Swagger Documentation](http://localhost:3002/security/swagger-ui/index.html)
* [API Shopping cart - Swagger Documentation](http://localhost:3003/shopping-cart/swagger-ui/index.html)

# hulk-store

Api service **hulk-store-api**

Api web ui **hulk-store-web**

In each folder review your README file.
