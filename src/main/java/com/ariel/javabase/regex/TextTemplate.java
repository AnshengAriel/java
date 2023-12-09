package com.ariel.javabase.regex;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextTemplate {

    private static final Pattern PATTERN = Pattern.compile("\\$\\{(\\w*)\\}");

    public static String format(String template, Map<String, Object> args) {
        if (template == null || template.isEmpty()) {
            return "null";
        }
        Matcher matcher = PATTERN.matcher(template);
        int end = 0;
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            int start = matcher.start();
            String key = matcher.group(1);
            builder.append(template, end, start)
                    .append(args.getOrDefault(key, "null"));
            end = matcher.end();
        }
        builder.append(template, end, template.length());
        return builder.toString();
    }

}
