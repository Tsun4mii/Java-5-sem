package bstu.shust.jprojectv2.repository;

import bstu.shust.jprojectv2.models.RentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRentFormRepository extends JpaRepository<RentForm, Long> {
    @Modifying
    void deleteById(Long id);
    @Modifying
    void deleteByUserIdAndScooterId(Long user_id, Long scooter_id);
    @Modifying
    void deleteByUserName(String userName);

    RentForm getById(Long id);
    List<RentForm> getAllByUserId(Long user_id);

}
