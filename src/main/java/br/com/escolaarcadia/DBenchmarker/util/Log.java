package br.com.escolaarcadia.DBenchmarker.util;

/**
 * Created by Danilo Souza Mor�es on 05/04/2015.
 */
public class Log {

    public static void err(String message) {
        System.err.println(message);
    }

    public static void err(String message, String errorMessage) {
        System.err.println(String.format("%s: %s", message, errorMessage));
    }

    public static void p(String message) {
        System.out.println(message);
    }
}
