

Feature: Ejemplo de request


Scenario: Prueba GET al endpoint
Given I sent a GET request to the https://api.github.com URI 
Then I get a list of 10 users  

@API
Scenario: Validar la cantidad de entradas en la respuesta 
Given I sent a GET request to the https://jsonplaceholder.typecode.com URI
Then I validate there are 10 items on the /users endpoint  
