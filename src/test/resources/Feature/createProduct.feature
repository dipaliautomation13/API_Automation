Feature: Create new products

  Scenario Outline: validate post api to create products
    Given request body with valid <name>
    When I do post call to create the product
    Then  I validate response code for successful
    And I validate response message of product created
    And I validate that the product is created in DB successfully
    Examples:
      | name |
#      name with single character
      | A    |
#      name with more than one and less than 50 characters
    |  Butter    |
#      name with exactly 50 character
    |  Apples Test Fifty Characters aaaaaaaaaaaaaaaaaaaaa          |
