
@API
Feature: Ejemplo de request

    Scenario: Prueba GET al endpoint
        Given I sent a GET request to the https://api.github.com URI
        Then I get a list of 10 users

    Scenario: Validar la cantidad de entradas en la respuesta
        Given I sent a GET request to the https://jsonplaceholder.typicode.com URI
        Then I validate there are 10 items on the /users endpoint


    Scenario: Validar que un elemento esta en la respuesta
        Given I sent a GET request to the https://jsonplaceholder.typicode.com URI
        Then I validate there is a value: Brent in the response at /users endpoint

    Scenario: validar un valor anidado dentro de la respuesta
        Given I sent a GET request to the https://jsonplaceholder.typicode.com URI
        Then I can validate the nested value: Kattie Turnpike on the response at users/ endpoint
