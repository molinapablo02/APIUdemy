@BraveNewCoin
Feature: BraveNewCoin API scenarios: POST GetToken

    Rule: When i send a POST request to the endpoint, i recieve a token i can use to make further authenticated calls.

        //retrieve = recuperar/rescatar

        Scenario: As a user, i can retrieve a token when making a valid POST request
            Given I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with a valid body to the /oauth/token endpoint
            Then I can validate i receive a valid token in the response

        Scenario: As a user, when i use an invalid API key i get, i get an HTTP Code Status 403
            Given I have a invalid API key for the https://bravenewcoin.p.rapidapi.com URI
            When I send a POST request with a valid body to the /oauth/token endpoint
            Then I receive a HTTP Code Status 403

        Scenario: As a user, when i send the POST request without "grant_type" i get an HTTP Code Status 400
            Given I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
            When I send the POST request without "grant_type" in it's body
            Then I receive an HTTP Code Status 400