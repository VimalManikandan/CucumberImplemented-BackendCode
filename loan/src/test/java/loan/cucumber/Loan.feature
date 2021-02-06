Feature: Loan Application

Scenario: Loan Controller Add Loan
When New Loan is added
Then Should return not null loan entity

Scenario: Loan Controller get Loan
When Loan is fetched
Then Should return not null loan entity

Scenario: Loan Controller update Loan
When New Loan is updated
Then Should return not null loan entity

Scenario: Loan Controller delete User 
When Loan is deleted
Then Should return Generic Response with success



 


