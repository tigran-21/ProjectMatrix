##  Automation project for testing ProjectMatrix App.


### How to run tests

1. To run maven test for login, you can use the following command

    ``` mvn test -Dtest=LoginPageTest -Dusername="<YourUsername>" -Dpassword="<YourPassword>"```

2. To run maven test for login page objects functionality, run following command

   ``` mvn test -Dtest=LoginPageObjectsFunctionalityTest```

3. To run maven tests in parallel using the command line, you can use the following command.

    ``` mvn test -Dsurefire.parallel=classes -Dusername="<YourUsername>" -Dpassword="<YourPassword>"```


### How to see result
1. to display the allure report in the browser you can use following command

    ``` allure serve allure-results```
