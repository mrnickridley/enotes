package budgetingexpenses.expensebudgeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budgetingexpenses.expensebudgeting.model.usersignup;

@Repository
public interface userrepository extends JpaRepository<usersignup, Integer> {
    usersignup findByUsername(String username);
    
}
