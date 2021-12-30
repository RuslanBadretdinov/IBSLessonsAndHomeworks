package Homework003_Companies;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

public class CompanyHandler {
    public static final File jsonFile = new File("G:\\Users\\qwerty\\IdeaProjects\\IBSLessonsAndHomeworks\\src\\main\\java\\Homework003_Companies\\CompanyHandler.java");
    public static List<Company> companies;
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //companies = mapper.readValue(jsonFile, new TypeReference<>(){});
            companies = Arrays.asList(mapper.readValue(jsonFile, Company[].class));
            System.out.println(companies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
