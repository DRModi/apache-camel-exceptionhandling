package com.learncamel.processor;

import org.apache.camel.Exchange;

public class GenerateCustomErrorResponseProcessor implements org.apache.camel.Processor {


    public void process(Exchange exchange) throws Exception {

        //Exception ex = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        System.out.println("Actual Exception Message : " + ex.getMessage());
        System.out.println("Actual Exception Class : " + ex.getClass());

        String failedEndpoint = exchange.getProperty(Exchange.FAILURE_ENDPOINT, String.class);
        System.out.println("Failed Endpoint : " + failedEndpoint);

        exchange.getIn().setBody("Exception caught, do check input string, it is custom generated response from Processor Class!!");
    }
}
