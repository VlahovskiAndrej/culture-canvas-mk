//package mk.ukim.finki.culturecanvasmk.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Data
//@Entity
//public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private int score;
//
//    private String description;
//
//    @ManyToOne
//    private Monument monument;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime timestamp;
//
//    public Review() {}
//
//    public Review(int score, String description, Monument monument, LocalDateTime timestamp) {
//        this.score = score;
//        this.description = description;
//        this.monument = monument;
//        this.timestamp = timestamp;
//    }
//
//    public String printTime(){
//        DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
//        return timestamp.format(CUSTOM_FORMATTER);
//    }
//
//
//}
//
