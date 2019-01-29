package org.csystem.thymeleafmodelsenderapp.util;

public class StringUtil {
    public static String changeWhiteSpaceWithPlusSignForConfiguration(String str)
    {
        return str.trim().replace(" ", "+");
    }
}
