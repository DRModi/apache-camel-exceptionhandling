package com.learncamel.routes;

import com.learncamel.bean.DataModifier;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class DefaultExceptionHandlerRoute extends RouteBuilder {


    public void configure() throws Exception {

        //errorHandler(defaultErrorHandler());

        //Retries for intermittent issues
        //errorHandler(defaultErrorHandler().maximumRedeliveries(2).redeliveryDelay(5000).retryAttemptedLogLevel(LoggingLevel.WARN));

        //Instead of retrying in regular interval, retries with multiplying/increase the time-frame in each attempt
        errorHandler(defaultErrorHandler().maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.WARN));



        from("direct:inputException")
                .log("input receive body is ${body}")
                .bean(new DataModifier(), "mapValues")
                .to("log:?level=INFO&showBody=true")
                .to("mock:outputWithException");
    }
}
