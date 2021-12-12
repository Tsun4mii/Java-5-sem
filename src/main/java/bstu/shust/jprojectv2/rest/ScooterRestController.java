package bstu.shust.jprojectv2.rest;

import bstu.shust.jprojectv2.dto.NameRequest;
import bstu.shust.jprojectv2.dto.ScooterRequestNoIdRent;
import bstu.shust.jprojectv2.exception.ControllerException;
import bstu.shust.jprojectv2.exception.ServiceException;
import bstu.shust.jprojectv2.models.Scooter;
import bstu.shust.jprojectv2.repository.UserRentFormRepository;
import bstu.shust.jprojectv2.service.ScooterService;
import bstu.shust.jprojectv2.service.UserRentFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScooterRestController {
    @Autowired
    private ScooterService scooterService;

    @PostMapping("/admin/createScooter")
    public ResponseEntity<?> createComputerStuff(@RequestBody ScooterRequestNoIdRent scooterRequestNoIdRent) throws ControllerException {
        Scooter stuff = new Scooter(
                scooterRequestNoIdRent.getName(),
                scooterRequestNoIdRent.getDescription(),
                scooterRequestNoIdRent.getCost(),
                scooterRequestNoIdRent.getExpirationDate()
        );
        try {
            scooterService.create(stuff);
            return new ResponseEntity<>(stuff, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/admin/deleteScooterByNameA")
    public ResponseEntity<?> deleteComputerStuffByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            Scooter man = scooterService.getByName(nameRequest.getName());
            scooterService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("/admin/getAllCompsForAdmin")
    public ResponseEntity<?> getAllCompsForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(scooterService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {

            throw new ControllerException(e);

        }
    }
    @PostMapping("/admin/isComputerStuffExistByName")
    public ResponseEntity<?> isComputerStuffExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!scooterService.existsByName(nameRequest.getName())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }

    }
}
