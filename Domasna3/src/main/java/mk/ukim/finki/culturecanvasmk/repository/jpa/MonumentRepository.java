package mk.ukim.finki.culturecanvasmk.repository.jpa;

import mk.ukim.finki.culturecanvasmk.model.Monument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonumentRepository extends JpaRepository<Monument, Long> {
    List<Monument> findAllByNameMkContainingIgnoreCaseOrNameEnContainingIgnoreCase(String name, String name1);

    List<Monument> findAllByCityIgnoreCase(String city);

    @Query("SELECT m.latitude FROM Monument m")
    List<Double> findAllLatitudes();

    @Query("SELECT m.longitude FROM Monument m")
    List<Double> findAllLongitudes();
}
