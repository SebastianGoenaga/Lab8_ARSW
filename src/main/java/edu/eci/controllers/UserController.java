package edu.eci.controllers;

import edu.eci.models.User;
import edu.eci.services.contracts.IUserServices;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserServices userServices;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUser(){
//        try{
            return new ResponseEntity<>(userServices.list(), HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        try{
            return new ResponseEntity<>(userServices.list(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUser(@RequestBody User user){
    	try{
            return new ResponseEntity<>(userServices.create(user), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@RequestBody User user){
    	try{
    		userServices.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUser(@RequestBody User user){
    	try{
    		userServices.deleteUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/userid", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteUserWithID(@RequestBody String id){
    	try{
    		userServices.removeUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
