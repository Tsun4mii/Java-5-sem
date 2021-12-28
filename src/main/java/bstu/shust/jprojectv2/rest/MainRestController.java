package bstu.shust.jprojectv2.rest;


import bstu.shust.jprojectv2.dto.AuthRequest;
import bstu.shust.jprojectv2.dto.AuthResponse;
import bstu.shust.jprojectv2.dto.RegistrationRequest;
import bstu.shust.jprojectv2.dto.UserResponse;
import bstu.shust.jprojectv2.exception.ControllerException;
import bstu.shust.jprojectv2.jwt.JwtProvider;
import bstu.shust.jprojectv2.models.User;
import bstu.shust.jprojectv2.service.MailSender;
import bstu.shust.jprojectv2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private MailSender mailSender;

    private static final Logger logger = Logger.getLogger(MainRestController.class);

    @PostMapping("/users")
    public List<User> getUsers() throws ControllerException {
        try {
            logger.debug("getting all users");

            return userService.findAll();
        } catch (Exception e) {
            logger.error("error get all users");

            throw new ControllerException("getUsers", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws ControllerException
    {
        try{
        logger.debug("try to login user");

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
            throw new ControllerException("not such user");
        }
        } catch (ControllerException e) {
            logger.error("error login");

            throw new ControllerException("No such user with this credentials", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest)
    {
        logger.debug("try to register user");
        if(!userService.existsUserByLogin(registrationRequest.getLogin()))
        {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setLogin(registrationRequest.getLogin());
            user.setEmail(registrationRequest.getEmail());
            user.setActive(true);
            user.setActivationCode(UUID.randomUUID().toString());
            userService.saveUser(user);
            if(!user.getEmail().isEmpty()){
                String message = String.format("Hello, %s!\n " +
                                "Welcome to my Java project! Please, visit next link: http://localhost:8080/activate/%s",
                        user.getLogin(), user.getActivationCode());
                mailSender.sendMail(user.getEmail(), "Activation code", message);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @GetMapping("/activate/{code}")
    public ModelAndView activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");

        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login2");
        return modelAndView;
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
