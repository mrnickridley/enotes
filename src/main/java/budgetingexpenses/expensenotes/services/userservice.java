package budgetingexpenses.expensenotes.services;

import budgetingexpenses.expensenotes.model.usersignup;

public interface userservice {
    usersignup getUserByUsername(String username);
    int findUserIdByUsername(String username);
}
