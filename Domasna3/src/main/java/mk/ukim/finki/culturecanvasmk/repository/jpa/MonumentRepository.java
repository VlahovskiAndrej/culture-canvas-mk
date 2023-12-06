package mk.ukim.finki.culturecanvasmk.repository.jpa;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonumentRepository extends JpaRepository<Monument, Long> {
    List<Monument> findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(String name, String name1);
    List<Monument> findAllByCityIgnoreCase(String city);
}
