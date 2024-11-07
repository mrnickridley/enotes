package budgetingexpenses.expensebudgeting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema="expensebudgeting", name="userexpenses")
@Getter
@Setter
public class userexpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="dateofexpense")
    private int expenseDate;

    @Column(name="descriptionofexpense")
    private String description;

    @Column(name="priceofexpense")
    private double price;

    @ManyToOne
    @JoinColumn
    private usersignup user;

    public userexpenses(){
        super();
    }
    public userexpenses(int id, int expenseDate, String description, int price){
            super();
            this.id = id;
            this.expenseDate = expenseDate;
            this.description = description;
            this.price = price;
        }

}
