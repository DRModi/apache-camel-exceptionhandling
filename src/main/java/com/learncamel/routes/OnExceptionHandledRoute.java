package com.learncamel.routes;

import com.learncamel.bean.DataModifier;
import com.learncamel.processor.GenerateCustomErrorResponseProcessor;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OnExceptionHandledRoute extends RouteBuilder {


    public void configure() throws Exception {

        // onException(RuntimeException.class,ApplicationException.class).handled(true).maximumRedeliveries(2).delay(2000).process(new GenerateErrorResponseProcessor()).log(LoggingLevel.WARN, "Exception in Processor caught here");

        onException(RuntimeException.class).handled(true).maximumRedeliveries(2).redeliveryDelay(3000).backOffMultiplier(2).process(new GenerateCustomErrorResponseProcessor())
                .log(LoggingLevel.WARN, "Exception being handled by Processor!!");

        /*onException(RuntimeException.class).continued(true).maximumRedeliveries(2).redeliveryDelay(2000).backOffMultiplier(2).process(new GenerateCustomErrorResponseProcessor())
                .to("log:?level=INFO&showCaughtException=true").log(LoggingLevel.WARN,"Exception being handled by processor!!");*/


        from("direct:inputOnException")
                .log("Received input message and Body is: ${body} : Done")
                .bean(new DataModifier(), "mapOnException")
                .to("log:?level=INFO&showBody=true");
    }
}
