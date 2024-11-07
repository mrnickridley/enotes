package budgetingexpenses.expensebudgeting.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import budgetingexpenses.expensebudgeting.model.userexpenses;
import budgetingexpenses.expensebudgeting.model.usersignup;
import budgetingexpenses.expensebudgeting.repository.expenserepo;
import budgetingexpenses.expensebudgeting.services.expenseservice;

@RestController
@RequestMapping(path="/expenses")
public class expensecontroller {
    @Autowired
    expenseservice serv;

    @Autowired
    expenserepo repo;
    
    @PostMapping(path="/newexpense")
    @CrossOrigin
    public ResponseEntity<userexpenses> createExpense(@RequestBody userexpenses expense) throws URISyntaxException{
        userexpenses result=repo.save(expense);
        return ResponseEntity.created(new URI("/expenses/newexpense" + result.getId())).body(result);
    }

    @GetMapping(path="/{user}")
    @CrossOrigin
    public List<userexpenses> findExpensesByUser(@PathVariable usersignup user){
        return repo.findByUser(user);
    }

    @DeleteMapping(path="/deleteexpense/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteExpense(@PathVariable int id){
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
