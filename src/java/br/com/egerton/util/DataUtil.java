package br.com.egerton.util;

public class DataUtil {

    public static boolean validaDatetime(String data) {
        if (data == null) {
            return false;
        }
        String regex = "\\d{4}-[01]\\d-[0-3]\\d [0-2]\\d:[0-5]\\d:[0-5]\\d(?:\\.\\d+)?Z?";
        return data.matches(regex);
    }
}
