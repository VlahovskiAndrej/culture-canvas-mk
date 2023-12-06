package mk.finki.ukim.mk.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Monument;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class DataHandler {
    public static List<String[]> csvData;
    public static List<Monument> monumentList;

    @PostConstruct
    public void init(){
        csvData = readCsvFile();

//    name,nameEn,region,city,municipality,postcode,suburb,lon,lat,address,,,,,,
        monumentList = csvData.stream().skip(1).map(r -> new Monument(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9])).toList();
    }

    private static List<String[]> readCsvFile() {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("src/main/resources/database/data.csv"), StandardCharsets.UTF_8))) {
            data = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }




}
