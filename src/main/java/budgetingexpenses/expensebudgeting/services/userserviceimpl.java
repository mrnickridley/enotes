package budgetingexpenses.expensebudgeting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import budgetingexpenses.expensebudgeting.model.usersignup;
import budgetingexpenses.expensebudgeting.repository.userrepository;

@Service
public class userserviceimpl implements userservice{
        @Autowired
        private userrepository uRepo;

        public usersignup getUserByUsername(String username){
                return uRepo.findByUsername(username);
        }

        public int findUserIdByUsername(String username){
                usersignup user = uRepo.findByUsername(username);
                return user != null ? user.getId() : null;
/*user != null: This checks whether the user object is not null. It's a condition that evaluates to either true or false.
?: This is the ternary operator. It acts like an if-else statement.
user.getId(): This part is executed if the condition (user != null) is true, meaning that user is not null. It retrieves the ID of the user object using the getId() method.
:: This separates the true and false parts of the ternary expression.
null: This is the value that will be returned if the condition (user != null) is false, meaning that user is null. */
        }

}
