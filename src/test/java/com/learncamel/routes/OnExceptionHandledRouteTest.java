package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class OnExceptionHandledRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){

        return new OnExceptionHandledRoute();
    }

    @Test
    public void onExceptionHandledCheck(){

        String inputString = null;
        String outputString = (String) template.requestBody("direct:inputOnException",inputString);

        String expectedString = "Exception caught, do check input string, it is custom generated response from Processor Class!!";

        System.out.println("Custom Response : "+ outputString);

        assertEquals(expectedString,outputString);
    }
}
