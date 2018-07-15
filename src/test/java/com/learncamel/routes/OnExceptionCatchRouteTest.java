package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class OnExceptionCatchRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder(){

        return new OnExceptionCatchRoute();
    }

    @Test(expected = RuntimeException.class)
    public void onExceptionCheck(){

        String inputString = null;
        String outputString = (String) template.requestBody("direct:inputOnException",inputString);

        assertEquals("",inputString);
    }
}
