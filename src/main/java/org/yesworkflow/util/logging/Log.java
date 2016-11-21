package org.yesworkflow.util.logging;

public abstract class Log {

    public static String instance(String type, String instance) {
        return type + "<" + instance + ">"; 
    }

}
