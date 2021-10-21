package bstu.shust.jprojectv2.repository;

import bstu.shust.jprojectv2.models.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    @Modifying
    void deleteById(int id);

    @Modifying
    void deleteByName(String name);

    Scooter getById(Long id);

    Scooter getByName(String name);

    boolean existsByName(String name);

    @Modifying
    @Query("update Scooter c set c.name=:name, c.description=:description, c.cost=:cost where c.id=:id")
    void updateScooterById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("cost") int cost
    );
}
