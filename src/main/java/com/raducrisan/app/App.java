package com.raducrisan.app;

import org.apache.commons.lang.time.StopWatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        StopWatch sw = new StopWatch();
        sw.start();
        CountUppercaseWords.initialize();
        CountUppercaseWords.start();
        sw.stop();
        System.out.println("Count UPPERCASE words started. The initialization took: " +  sw);
    }
}
