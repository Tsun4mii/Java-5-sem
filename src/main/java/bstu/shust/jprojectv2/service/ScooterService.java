package bstu.shust.jprojectv2.service;

import bstu.shust.jprojectv2.exception.RepositoryException;
import bstu.shust.jprojectv2.exception.ServiceException;
import bstu.shust.jprojectv2.models.Scooter;
import bstu.shust.jprojectv2.repository.ScooterRepository;
import bstu.shust.jprojectv2.service.interfaces.IScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScooterService implements IScooterService{
    @Autowired
    private ScooterRepository scooterRepository;

    @Override
    public void deleteById(Long id) {
        scooterRepository.deleteById(id);
    }

    @Override
    public Scooter create(Scooter Scooter)throws ServiceException {
        return scooterRepository.save(Scooter);
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        try {
            return scooterRepository.existsByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Scooter> getAll()throws ServiceException {
        return scooterRepository.findAll();
    }

    @Override
    public void deleteByName(String name)throws ServiceException {
        try {
            scooterRepository.deleteByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Scooter getById(Long id)throws ServiceException {
        try {
            return scooterRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Scooter getByName(String name)throws ServiceException {
        try {
            return scooterRepository.getByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void updateScooterById(Long id, String name, String description, int cost ) throws ServiceException{
        try {
            scooterRepository.updateScooterById(id, name, description, cost);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}
