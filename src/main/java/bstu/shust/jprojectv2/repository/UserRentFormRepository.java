package bstu.shust.jprojectv2.repository;

import bstu.shust.jprojectv2.exception.RepositoryException;
import bstu.shust.jprojectv2.exception.ServiceException;
import bstu.shust.jprojectv2.models.RentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRentFormRepository extends JpaRepository<RentForm, Long> {
    @Modifying
    void deleteById(Long id);
    @Modifying
    void deleteByUserIdAndScooterId(Long user_id, Long computerStuff_id)throws RepositoryException;
    @Modifying
    @Transactional
    void deleteByUserName(String userName)throws RepositoryException;

    RentForm getById(Long id);
    List<RentForm> getAllByUserId(Long user_id)throws RepositoryException;
    boolean existsByScooterId(Long computerStuff_id)throws RepositoryException;
    boolean existsByUserId(Long user_id)throws RepositoryException;
    List<RentForm> getAllByRent(boolean rent)throws RepositoryException;
    List<RentForm> getAllByScooterExpirationDateLessThan(Date computerStuff_expirationDate)throws RepositoryException;
    @Modifying
    @Query("update RentForm urf set urf.rent =:rent  where urf.id =:id ")
    void setUserRentFormById(@Param("id") Long id, @Param("rent") boolean rent)throws RepositoryException, ServiceException;

}
