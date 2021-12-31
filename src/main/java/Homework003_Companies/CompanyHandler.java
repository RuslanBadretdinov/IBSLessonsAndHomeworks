package Homework003_Companies;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyHandler {
    public static File jsonFile = new File("G:\\Users\\qwerty\\IdeaProjects\\IBSLessonsAndHomeworks\\src\\main\\java\\Homework003_Companies\\shares");
    public ArrayList<Company> companies;
    public static void main(String[] args) {
        CompanyHandler companyHandler;
        ObjectMapper mapper = new ObjectMapper();
        try {
            companyHandler = mapper.readValue(jsonFile, CompanyHandler.class);
            companyHandler.printCompaniesNameAndFounded();
            companyHandler.printSharesLessToday();
            companyHandler.printCompaniesByDate(args[0]);
            companyHandler.printSharesByCurrency(args[1]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printCompaniesNameAndFounded() {
        System.out.println("Вывод списка: имя + дата основания:");
        this.companies.forEach((company -> System.out.println("\tКраткое название "+company.getName()+" - "+"Дата основания "+company.getFounded())));
        System.out.println("\n");
    }

    public void printSharesLessToday() {
        System.out.println("Вывод списка просроченных ценнных бумаг на текущий день:");
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        int sum = 0;
        for(Company company : companies) {
            company.getSecurities().stream().filter(share-> LocalDate.parse(share.getDate(), dateTimeFormatter).isBefore(today)).
                    forEach(share -> System.out.print("\t"+share.getCode()+"\t"+share.getDate()+"\t"));
            System.out.println("\t\tИмя компании:"+ company.getName());
        }
        for(Company company : companies) sum += company.getSecurities().stream().filter(share-> LocalDate.parse(share.getDate(), dateTimeFormatter).isBefore(today)).count();

        System.out.println("\nОбщее количество просроченных ценных бумаг на текущий день:"+sum);
    }

    public void printCompaniesByDate(String date) {
        System.out.println("\n\nВывод списка компаний с их датой основания после введённой даты: "+date);
        try {
            LocalDate localDate = checkDateByFormat(date);
            if (localDate != null) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                if (!(companies.stream().filter(company-> LocalDate.parse(company.getFounded(), dateTimeFormatter).isAfter(localDate)).
                        peek(company -> System.out.println("\t"+company.getName()+"\t"+company.getFounded())).count() > 0)) {
                    System.out.println("Отсутствуют элементы списка компаний с их датой основания после введённой даты:\t");
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Была введена не верная дата - было превышено предельное значение параметра даты");
            System.err.println(e.getMessage());
        }
    }

    public LocalDate checkDateByFormat(String date) {
        Pattern pattern = Pattern.compile("^[1-3]\\d[./][01]\\d(([/.]\\d{4})|([/.]\\d{2}))\\z");
        Matcher matcher = pattern.matcher(date);
        if  (matcher.matches()) {
            System.out.println("Введённая дата имеет записи вида: ДД.MM.ГГГГ, ДД/MM/ГГГГ, ДД/MM/ГГ");
            String dateFormat = date.replaceFirst("\\d\\d", "d").replaceFirst("\\d\\d", "MM").replaceAll("\\d","y");
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
        } else {
            System.out.println("Введённая дата отлична от записи вида: ДД.MM.ГГГГ, ДД/MM/ГГГГ, ДД/MM/ГГ");
            return null;
        }
    }

    public void printSharesByCurrency(String currency) {
        System.out.println("\n\nВывод списка id компаний и коды их ценных бумаг, использующих валюту: "+currency);
        companies.stream().
                peek(company -> System.out.println(company.getSecurities().stream().anyMatch(share -> share.getCurrency().contains(currency)) ?
                        "\t id Компании: "+company.getId()+";\tВывод code ценной бумаги: ":
                        "")).
                forEach(company -> company.getSecurities().stream().filter(share -> share.getCurrency().contains(currency)).
                        forEach(share -> System.out.println("\t\t"+share.getCode())));
    }
}
