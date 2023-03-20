package com.example.lztest.controller;

import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import static com.example.lztest.service.impl.DateHighlightServiceImpl.markDatesRed;

@Controller
@RequestMapping("/api")
@Api(value = "Dates Highlight Controller", tags = {"Date"})
public class DateHighlightController {
    private static final String DATA_PATH = "src/main/resources/test-data/html/";
    @SneakyThrows
    @RequestMapping(value = "/mark-dates-red/{htmlFileName}")
    public String markHtmlDatesRed(@PathVariable("htmlFileName") String htmlFileName) {
        String res = htmlFileName + "-red.html";
        String outputFile = DATA_PATH + res;
        String html = readFile(DATA_PATH + htmlFileName + ".html");
        String markedRedHtml = markDatesRed(html);
        writeFile(outputFile, markedRedHtml);
        return "redirect:/" + res;
    }

    @SneakyThrows
    private static String readFile(String filename) {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    @SneakyThrows
    private static void writeFile(String filename, String content) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(content);
        bw.close();
    }

}
