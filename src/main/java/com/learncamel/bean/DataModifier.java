package com.learncamel.bean;


import java.util.logging.Logger;

public class DataModifier {

    Logger log = Logger.getLogger(DataModifier.class.getName());

    public String mapValues(String inputString) throws Exception {

        String outputString = null;

        try {
            outputString = inputString.replace(",", ":");

        }catch (RuntimeException ex){
            log.severe("RuntimeError due to inputString is null : " + ex);
            throw ex;

        }catch (Exception ex){
            log.severe("Other Exception : "+ex);
            throw ex;
        }

        return outputString;
    }


    public String mapOnException(String inputString) throws Exception {

        String outputString = null;

        try {
            outputString = inputString.replace(",", ":");

        }catch (RuntimeException ex){
            log.severe("RuntimeError due to inputString is null : " + ex);
            throw ex;

        }catch (Exception ex){
            log.severe("Other Exception : "+ex);
            throw ex;
        }

        return outputString;
    }



}
