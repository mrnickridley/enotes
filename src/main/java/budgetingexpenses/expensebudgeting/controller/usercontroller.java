package budgetingexpenses.expensebudgeting.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; /*@PathVariable retrieves data from URL path*/
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;/*@ResquestBody extracts HTTP request body data */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import budgetingexpenses.expensebudgeting.model.usersignup;
import budgetingexpenses.expensebudgeting.repository.userrepository;
import budgetingexpenses.expensebudgeting.services.userservice;

@RestController
@RequestMapping(path="/signup")
public class usercontroller {

    @Autowired
    userservice serv;

    @Autowired
    userrepository repo;

    @PostMapping(path="/saveuser")
    @CrossOrigin
    public ResponseEntity<usersignup> createUser(@RequestBody usersignup user) throws URISyntaxException{
        usersignup result=repo.save(user);
        return ResponseEntity.created(new URI("/category/saveuser" + result.getUsername())).body(result);
    } /* ResponseEntity: This is a Spring framework class that represents an HTTP response, including status code, headers, and body. It is often used to build HTTP responses in REST APIs.

    .created(URI location): This method is used to indicate that a new resource has been successfully created. It returns a 201 Created HTTP status code. The argument (new URI(...)) specifies the URI of the newly created resource.
    
    new URI("/category/saveuser" + result.getUsername()): This constructs the URI where the newly created resource can be accessed. It uses the getUsername() method from the result object to append the username to the URI. For example, if the username is "john", the URI would look like "/category/saveuser/john".*/


/*
    @GetMapping(path="/getuser/{id}")
    @CrossOrigin
    public ResponseEntity<?> getUser(@PathVariable int id){
        Optional<usersignup> finduser = repo.findById(id);
        return finduser.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
*/

    @GetMapping(path="/finduser/{username}")
    @CrossOrigin
    public ResponseEntity<usersignup> getUserByUsername(@PathVariable String username){
        usersignup user = serv.getUserByUsername(username);
        System.out.println("This is my user" + " " +username);
        
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
        }

    @GetMapping(path="/{username}")
    @CrossOrigin
    public ResponseEntity<Integer> getUserIdByUsername(@PathVariable String username){
        Integer userID = serv.findUserIdByUsername(username);
        System.out.println("This is my user ID" + " " + userID);
        
        if(userID != null){
            return ResponseEntity.ok(userID);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        }
    }

