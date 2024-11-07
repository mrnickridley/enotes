package budgetingexpenses.expensebudgeting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budgetingexpenses.expensebudgeting.model.userexpenses;
import budgetingexpenses.expensebudgeting.model.usersignup;





@Repository
public interface expenserepo extends JpaRepository<userexpenses, Integer>{
    List<userexpenses> findByUser(usersignup user);
    
}
    

