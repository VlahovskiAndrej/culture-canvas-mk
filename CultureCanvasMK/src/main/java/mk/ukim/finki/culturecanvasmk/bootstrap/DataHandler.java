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
import java.util.Random;

@Component
public class DataHandler {
    public static List<String[]> csvData;
    public static List<Monument> monumentList;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultImageUrl = "https://www.shutterstock.com/image-photo/aerial-view-samuels-fortress-plaosnik-260nw-1426740269.jpg";

    private final List<String> listImages;

    public DataHandler(UserRepository userRepository, PasswordEncoder passwordEncoder, List<String> listImages) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.listImages = listImages;
    }

//    CREATES ADMIN
//    username: admin
//    password: admin

//    CREATES USER
//    username: user
//    password: user
    @PostConstruct
    public void init() {

        fill(listImages);
        Random rand = new Random();

        csvData = readCsvFile();

        monumentList = csvData.stream().skip(1).map(r -> new Monument(r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7], r[8], r[9], listImages.get(rand.nextInt(listImages.size())))).toList();
        if (userRepository.count() == 0) {
            User user = new User("admin",
                    passwordEncoder.encode("admin"),
                    "culturecanvasmk@gmail.com",
                    Role.ADMIN, "");
            user.setRegistered(true);
            userRepository.save(user);

            User user2 = new User("user",
                    passwordEncoder.encode("user"),
                    "culturecanvasmk@gmail.com",
                    Role.USER, "");
            user2.setRegistered(true);
            userRepository.save(user2);

        }
    }
//  Reads the monuments from data.csv into Database
    private static List<String[]> readCsvFile() {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("src/main/resources/data/data.csv"), StandardCharsets.UTF_8))) {
            data = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void fill(List<String> listImages){
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/13/17/da/a8/caption.jpg?w=500&h=400&s=1");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/13/43/91/17/received-10156460186043739.jpg?w=500&h=400&s=1");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0d/ac/47/0b/macedonia-stone-bridge.jpg?w=500&h=400&s=1");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/f7/f4/10/monastery-of-saint-naum.jpg?w=500&h=400&s=1");
        listImages.add("https://www.traveltourxp.com/wp-content/uploads/2017/04/Historic-Sites-Of-Republic-Of-Macedonia.jpg");
        listImages.add("https://chasingthedonkey.b-cdn.net/wp-content/uploads/2021/03/Samuels-Fortress-in-Ohrid_macedonia_Depositphotos_320155178_s-2019.jpeg");
        listImages.add("https://www.mywanderlust.pl/wp-content/uploads/2023/01/places-to-visit-in-macedonia-42.jpg");
        listImages.add("https://skopjeguide.com/wp-content/uploads/2022/04/Historic-Places-and-Things-to-See-in-Skopje.jpeg");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/09/99/54/ec/heraclea.jpg?w=500&h=-1&s=1");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/13/4a/a6/ac/received-10156462498193739.jpg?w=500&h=400&s=1");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/17/c5/dd/9d/img-20190601-221451-842.jpg?w=500&h=-1&s=1");
        listImages.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSr0RMoOSITeNteYnai_YRYTpvEzuWs6MhPPg&usqp=CAU");
        listImages.add("https://www.mywanderlust.pl/wp-content/uploads/2023/01/places-to-visit-in-macedonia-53.jpg");
        listImages.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/01/08/69/ce/daut-pasha-hamam-bathhouse.jpg?w=500&h=400&s=1");
        listImages.add("https://beinmacedonia.com/wp-content/uploads/2018/08/IMG_4201.jpg");
        listImages.add("https://cdn.britannica.com/36/163536-050-997CDBDD/ruins-Macedonia-Heraclea-Lyncestis-Bitola.jpg");
        listImages.add("https://www.mywanderlust.pl/wp-content/uploads/2023/01/places-to-visit-in-macedonia-42.jpg");
        listImages.add("https://demofree.sirv.com/nope-not-here.jpg");
    }


}
