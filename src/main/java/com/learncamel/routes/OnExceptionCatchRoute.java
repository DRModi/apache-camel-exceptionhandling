package com.learncamel.routes;

import com.learncamel.bean.DataModifier;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OnExceptionCatchRoute extends RouteBuilder {


    public void configure() throws Exception {

        onException(RuntimeException.class).maximumRedeliveries(2).redeliveryDelay(5000).backOffMultiplier(2)
                .log(LoggingLevel.WARN,"Exception from the bean class are being caught here!!");

        from("direct:inputOnException")
                .log("Received input message and Body is: ${body} : Done")
                .bean(new DataModifier(), "mapOnException")
                .to("log:?level=INFO&showBody=true");
    }
}
