# learncamel-ExceptionHandling

(1) In order to pass the test case, mentioned Expected = RuntimeException.class

(2) by default on the route's configure() method, following error handler class will catch the exception and throws back to the producer (mean from method).

            errorHandler(defaultErrorHandler());

(3) Handler Retries for intermittent issue in integration: Using default error handler class
        5000 = milliseconds
        2 = number of retries after first failed attempt
        WARN = To differentiate with other logger, logged this entries as WARN

        errorHandler(defaultErrorHandler().maximumRedeliveries(2).redeliveryDelay(5000).retryAttemptedLogLevel(LoggingLevel.WARN));

(4) Instead of retrying in regular interval, retries with multiplying/increase the time-frame in each attempt
    - With this approach not flooding the sever with requests...will help in chances of server recovery..

          errorHandler(defaultErrorHandler().maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.WARN));


Disadvantages with Error handler compare to on exception:
**********************************************************
    - it does not allow to act differently according to the exception, means runtime exception vs interrupted exception.

(5) onException can be defined as below: where you can define the expected Exception class like RuntimeException, SQLException..etc

    onException(RuntimeException.class).maximumRedeliveries(2).redeliveryDelay(5000).backOffMultiplier(2).log(LoggingLevel.WARN,"Exception from the bean class are being caught here!!");

    onException(SQLException.class).log(LoggingLevel.ERROR, "Message SQL Exception being caught in connection class"

(6) Exception Handled using processors: and generated custom response.
     -- .process define the processor bean
     -- .handled define the exception handled

    onException(RuntimeException.class).handled(true).maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).process(new GenerateCustomErrorResponseProcessor())
                    .log(LoggingLevel.WARN, "Exception being handled by Processor!!");

    In processor class, we can retrieve all the information from exchange object

        Examples : Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);


Note: you can use route as well part of it,like (.to) another specific route can be triggered, like call Kafka topics to which error message being send it will have
consumer on top of it. then consumer will decide to resent to publisher/source again or track it separately.

onException(RuntimeException.class).handled(true).maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).process(new GenerateCustomErrorResponseProcessor())
                    .to("kafka:errortopic").log(LoggingLevel.WARN, "Exception being handled by Processor!!");


(7) Continue the route and ignore any exception

    -- .continued(true)

        onException(RuntimeException.class).continued(true).to("log:?level=INFO&showCaughtException=true")
                .log(LoggingLevel.WARN,"Exception caught but flow should continue!!");







