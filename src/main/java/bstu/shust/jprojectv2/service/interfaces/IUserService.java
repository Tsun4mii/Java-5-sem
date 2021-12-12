package bstu.shust.jprojectv2.service.interfaces;

import bstu.shust.jprojectv2.exception.ServiceException;
import bstu.shust.jprojectv2.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    User saveUser(User user);
    User findByLogin(String login);
    List<User> findAll();
    User findById(Long id);
    User findByLoginAndPassword(String login, String password);
    boolean existsUserByLogin(String login);
    boolean existsUserByLoginAndPassword(String login, String password);
}
