package mk.ukim.finki.culturecanvasmk.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.culturecanvasmk.model.Monument;
import mk.ukim.finki.culturecanvasmk.model.Role;
import mk.ukim.finki.culturecanvasmk.model.User;
import mk.ukim.finki.culturecanvasmk.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;

@Component
public class DataHandler {
    public static List<String[]> csvData;
    public static List<Monument> monumentList;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultImageUrl = "https://www.shutterstock.com/image-photo/aerial-view-samuels-fortress-plaosnik-260nw-1426740269.jpg";

    public DataHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        csvData = readCsvFile();

//    name,nameEn,region,city,municipality,postcode,suburb,lat,lon,address,,,,,,
        monumentList = csvData.stream().skip(1).map(r -> new Monument(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9], defaultImageUrl)).toList();
        if (userRepository.count() == 0) {
            User user = new User("admin",
                    passwordEncoder.encode("admin"),
                    "culturecanvasmk@gmail.com",
                    Role.ADMIN, "");
            user.setRegistered(true);
            userRepository.save(user);
        }
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
