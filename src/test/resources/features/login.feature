#Author: camiloeduardo.ortiz@gmail.com

@login
Feature: Facebook login
  Login into facebook and post a test status
  
  Scenario Outline: Login into facebook and post
    Given I am in the facebook main page
    When I login using '<username>' and '<password>'
    Then I should be able to post a status message
    
    Examples:
    |	username												|	password	|
    |	camiloeduardo.ortiz@uptc.edu.co	|	Asd1234!	|

