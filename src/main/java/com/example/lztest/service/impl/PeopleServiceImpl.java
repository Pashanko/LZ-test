package com.example.lztest.service.impl;

import com.example.lztest.service.PeopleService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    public static JSONArray convertDataToJSONArray(String input) throws JSONException {
        JSONArray people = new JSONArray();
        String[] lines = input.split("\\n");

        for (String line : lines) {
            String clearLine = line.replaceAll("\\([^)]*\\)", "");
            String[] fields = clearLine.split("\\s+");

            String name = "";
            String surname = "";
            String personType = "";
            List<String> refinedFields = new ArrayList<>();
            for (String f: fields) {
                if(!isRedundantWord(f) && !isPersonType(f))
                    refinedFields.add(f);
                if(isPersonType(f))
                    personType = f;
            }
            if (refinedFields.size() > 1) {
                name = refinedFields.get(0);
                surname = String.join(" ", refinedFields.subList(1, refinedFields.size()));
            } else if (refinedFields.size() == 1) {
                surname = refinedFields.get(0);
            }

            // Create a JSON object for the person and add it to the array
            JSONObject person = new JSONObject();
            person.put("name", name);
            person.put("surname", surname);
            person.put("persontype", personType);
            people.put(person);
        }

        return people;
    }

    private static boolean isPersonType(String word) {
        List<String> types = Arrays.asList("mr", "mr.", "mrs", "mrs.", "ms", "ms.", "sir", "lady", "lord", "his", "her", "dame");
        return types.contains(word.toLowerCase());
    }

    private static boolean isRedundantWord(String word) {
        List<String> types = Arrays.asList("upper", "judge", "justice", "of", "county", "honour", "tribunal", "deputy",
                "counsel", "qc", "q.c.", "rolls", "master", "the", "honour", "family", "division", "president", "court",
                "vice-president", "appeal");
        return types.contains(word.toLowerCase());
    }

    public static JSONArray convertFileDataToJSONArray(String fileName) throws JSONException, IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        StringBuilder res = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if(line.contains(",")) {
                res.append(line.substring(0, line.indexOf(','))).append("\n");
                res.append(line.substring(line.indexOf(',') + 1, line.length() - 1)).append("\n");
            }
            else {
                res.append(line).append("\n");
            }
        }

        return PeopleServiceImpl.convertDataToJSONArray(res.toString());
    }
}
