package mk.ukim.finki.culturecanvasmk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer score;
    @Column(length = 4000)
    private String description;

    // @ManyToOne
    //private Monument monument;


    public Review(Integer score, String description) {
        this.score = score;
        this.description = description;
    }
    public Review()
    {
        this.score=0;
    }
}
