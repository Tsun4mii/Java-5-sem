package bstu.shust.jprojectv2.service.interfaces;

import bstu.shust.jprojectv2.models.Scooter;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IScooterService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Scooter create(Scooter computerStuff)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    boolean existsByName(String name) throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    List<Scooter> getAll()throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
    @Transactional
    void deleteByName(String name)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    Scooter getById(Long id)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    Scooter getByName(String name)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;

    @Transactional
    void updateScooterById(
            Long id,
            String name,
            String description,
            int cost)throws ServiceException, bstu.shust.jprojectv2.exception.ServiceException;
}
