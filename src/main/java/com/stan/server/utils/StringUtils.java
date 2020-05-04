package com.stan.server.utils;

public class StringUtils {

    public static int[] DotStringToIntArray(String s) {
        if (s != null && !s.equals("")) {
            String[] strings = org.apache.commons.lang3.StringUtils.split(s, ",");
            int[] p = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                p[i] = Integer.parseInt(strings[i]);
            }
            return p;
        }
        return new int[0];
    }
}
