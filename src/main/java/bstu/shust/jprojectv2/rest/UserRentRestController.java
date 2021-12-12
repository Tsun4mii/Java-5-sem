package bstu.shust.jprojectv2.rest;

import bstu.shust.jprojectv2.dto.DateRequest;
import bstu.shust.jprojectv2.exception.ControllerException;
import bstu.shust.jprojectv2.exception.ServiceException;
import bstu.shust.jprojectv2.service.UserRentFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRentRestController {
    @Autowired
    private UserRentFormService userRentFormService;
    @PostMapping("/admin/getAllByComputerStuffExpirationDateLessThan")
    public ResponseEntity<?> getAllByComputerStuffExpirationDateLessThan(@RequestBody DateRequest dateRequest) throws ControllerException {
        try {
            return new ResponseEntity<>(userRentFormService.getAllByScooterExpirationDateLessThan(dateRequest.getDate()), HttpStatus.FOUND);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

    }
}
