package com.bridglabz;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class OpenCsvAndGsonTestor {
    private static final String SAMPLE_CSV_PATH = "F:\\example1.csv";
    private static final String SAMPLE_JSON_PATH = "F:\\example1.json";

    public static void main(String[] args) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_PATH));
            CsvToBeanBuilder<CSVUser> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVUser.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVUser> csvToBean = csvToBeanBuilder.build();
            List<CSVUser> csvUsers = csvToBean.parse();
            Gson gson = new Gson();
            String json = gson.toJson(csvUsers);
            FileWriter writer = new FileWriter(SAMPLE_JSON_PATH);
            writer.write(json);
            writer.close();
            BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_PATH));
            CSVUser[] userObj = gson.fromJson(br, CSVUser[].class);
            List<CSVUser> csvUsersList = Arrays.asList(userObj);
        } catch (Exception e) {
        }
    }
}
