package budgetingexpenses.expensenotes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema="expensebudgeting", name="usersignup")
@Getter
@Setter
public class usersignup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    
    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;


    public usersignup(){
        super();
}

    public usersignup(int id, String username, String firstname, String lastname, String email){
        super();
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
