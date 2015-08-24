package com.thevolume360.utils;


public class StringUtils {

    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    private static String elegibleChars = "ABDEFGHJKLMRSTUVWXYabdefhjkmnrstuvwxy23456789";

    public static String generateRandomString(int stringLength) {

        char[] chars = elegibleChars.toCharArray();
        final StringBuffer finalString = new StringBuffer();

        for (int i = 0; i < stringLength; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);
        }

        return finalString.toString();
    }

    public static String getTrimmedString(String text, int lengthToTrim) {
        if (text.length() <= lengthToTrim) {
            return text;
        }

        return text.substring(0, lengthToTrim);
    }
}
