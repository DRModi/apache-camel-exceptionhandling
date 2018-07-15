package com.learncamel.routes;

import com.learncamel.bean.DataModifier;
import com.learncamel.processor.GenerateCustomErrorResponseProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OnExceptionContinueRoute extends RouteBuilder {


    public void configure() throws Exception {

        onException(RuntimeException.class).continued(true).to("log:?level=INFO&showCaughtException=true")
                .log(LoggingLevel.WARN,"Exception caught but flow should continue!!");


        from("direct:inputOnException")
                .log("Received input message and Body is: ${body} : Done")
                .bean(new DataModifier(), "mapOnException")
                .log("After bean route message and Body is: ${body} : Done")
                .to("log:?level=INFO&showBody=true&showCaughtException=true");
    }
}
