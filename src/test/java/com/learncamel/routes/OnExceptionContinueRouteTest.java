package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class OnExceptionContinueRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){

        return new OnExceptionContinueRoute();
    }

    @Test
    public void onExceptionContinueCheck(){

        String inputString = null;
        String outputString = (String) template.requestBody("direct:inputOnException",inputString);

        String expectedString = null;

        System.out.println("Custom Response : "+ outputString);

        assertEquals(expectedString,outputString);
    }
}
