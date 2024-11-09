package budgetingexpenses.expensenotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budgetingexpenses.expensenotes.model.userexpenses;
import budgetingexpenses.expensenotes.model.usersignup;





@Repository
public interface expenserepo extends JpaRepository<userexpenses, Integer>{
    List<userexpenses> findByUser(usersignup user);
    
}
    

