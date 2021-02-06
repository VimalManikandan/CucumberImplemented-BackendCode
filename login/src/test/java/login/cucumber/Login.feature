Feature: Application Login

Scenario: UserAuth API test
When user is logging in
Then Should get a http status code of 200
When user is loggged out
Then Should get a http status code of 200

Scenario: UserAPI add User test
When New User is added
Then Should return not null user entity

Scenario: UserAPI get User test
When User is fetched
Then Should return not null user entity

Scenario: UserAPI update User test
When New User is updated
Then Should return not null user entity

Scenario: UserAPI delete User test
When User is deleted
Then Should return Generic Response with success



 


