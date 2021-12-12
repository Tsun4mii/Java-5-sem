package bstu.shust.jprojectv2.rest;


import bstu.shust.jprojectv2.dto.AuthRequest;
import bstu.shust.jprojectv2.dto.AuthResponse;
import bstu.shust.jprojectv2.dto.RegistrationRequest;
import bstu.shust.jprojectv2.dto.UserResponse;
import bstu.shust.jprojectv2.exception.ControllerException;
import bstu.shust.jprojectv2.jwt.JwtProvider;
import bstu.shust.jprojectv2.models.User;
import bstu.shust.jprojectv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/users")
    public List<User> getUsers()
    {
        return userService.findAll();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest)
    {
        User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        if(user != null)
        {
            String token = jwtProvider.generateToken(user.getLogin());
            AuthResponse response = new AuthResponse(token, user.getUserRole().getName());
            System.out.println(user.getUserRole().getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest)
    {
        if(!userService.existsUserByLogin(registrationRequest.getLogin()))
        {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            user.setEmail(registrationRequest.getEmail());
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
    @PostMapping("/authorized")
    public ResponseEntity<?> isAuthorized() throws ControllerException {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/getUser")
    public UserResponse getUser(@RequestHeader(name = "Authorisation") String jwt) throws ControllerException {
        try {

            String userName = jwtProvider.getLoginFromToken(jwt.substring(7));
            User user = userService.findByLogin(userName);

            return new UserResponse(user.getId(), user.getLogin(), user.getUserRole().getName());
        } catch (Exception e) {
            throw new ControllerException("getUser", e);
        }
    }
}
