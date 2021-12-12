package bstu.shust.jprojectv2.service.interfaces;

import bstu.shust.jprojectv2.exception.RepositoryException;
import bstu.shust.jprojectv2.models.RentForm;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IUserRentFormService {
    @Transactional
    void deleteById(Long id)throws ServiceException;
    @Transactional
    void deleteByUserIdAndScooterId(Long user_id, Long computerStuff_id) throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    RentForm create(RentForm userRentForm)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
    boolean existsByScooterId(Long computerStuff_id) throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
    RentForm getById(Long id)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    List<RentForm> getAllByUserId(Long user_id)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
    List<RentForm> getAllByRent(boolean rent)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    List<RentForm> getAllByScooterExpirationDateLessThan(Date computerStuff_expirationDate)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
    @Transactional
    void setUserRentFormById(Long id, boolean rent)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
}
