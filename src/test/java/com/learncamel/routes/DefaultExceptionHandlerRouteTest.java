package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class DefaultExceptionHandlerRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){

        return new DefaultExceptionHandlerRoute();
    }


    @Test(expected = RuntimeException.class)
    public void checkExcetionHandlerTest(){

        String expectedOutput = "I:dont:expect:result";
        String inputString = null;

        String outputString = template.requestBody("direct:inputException", inputString, String.class);

        assertEquals(outputString,expectedOutput);
    }
}
