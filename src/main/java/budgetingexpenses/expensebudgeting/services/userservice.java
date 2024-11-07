package budgetingexpenses.expensebudgeting.services;

import budgetingexpenses.expensebudgeting.model.usersignup;

public interface userservice {
    usersignup getUserByUsername(String username);
    int findUserIdByUsername(String username);
}
