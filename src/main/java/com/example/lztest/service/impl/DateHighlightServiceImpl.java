package com.example.lztest.service.impl;

import com.example.lztest.service.DateHighlightService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DateHighlightServiceImpl implements DateHighlightService {
    public static String markDatesRed(String htmlFile) {
        String monthPattern = "(Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)";
        String datePattern = "(?:\\d{1,2}/\\d{1,2}/\\d{4})|(?:\\d{1,2}\s" + monthPattern + "\s\\d{4})|(?:" + monthPattern + "\s\\d{1,2}\s\\d{4})"; // pattern for MM/DD/YYYY or MM/DD/YYYY format
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(htmlFile);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "<span style='background-color:red;'>$0</span>");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
