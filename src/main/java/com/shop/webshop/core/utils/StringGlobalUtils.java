package com.shop.webshop.core.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
public class StringGlobalUtils {
    public String covertToString(String value) {
        value = value.trim();
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d").replaceAll("/", "-");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String removeSpaces(String value) {
        String str = value.trim();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') stb.append(str.charAt(i));
            else if (str.charAt(i) == ' ') {
            }
        }
        return str;
    }
}
