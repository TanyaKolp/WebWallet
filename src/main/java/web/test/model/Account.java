package web.test.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tania on 23.04.17.
 */
@Entity
@Table(name = "Account")
public class Account extends Model{

    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_balance")
    private Double balance;
    @Column(name = "account_type")
    private String type;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    private BudgetHead budgetHead;

    @OneToMany(mappedBy = "account")
    private List<ServicesSection> servicesSections;

    public List<ServicesSection> getServicesSections() {
        return servicesSections;
    }

    public void setServicesSections(List<ServicesSection> servicesSections) {
        this.servicesSections = servicesSections;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BudgetHead getBudgetHead() {
        return budgetHead;
    }

    public void setBudgetHead(BudgetHead budgetHead) {
        this.budgetHead = budgetHead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
