package web.test.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tania on 23.04.17.
 */
@Entity
@Table(name = "Account")
public class Account extends Model {

    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "account_balance")
    private Double balance;
    @Column(name = "account_type")
    private String type;
    @Column(name = "account_payrollData")
    private Date payrollDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    private BudgetHead budgetHead;

    @OneToMany(mappedBy = "account")
    private List<TypeService> typeServices;

    public Account() {
        this.balance = 0.0;
        this.type = "Default";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        this.payrollDate = cal.getTime();
    }

    public Account(Double balance, String type) {
        this.balance = balance;
        this.type = type;
    }

    public Date getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(Date payrollDate) {
        this.payrollDate = payrollDate;
    }

    public List<TypeService> getTypeServices() {
        return typeServices;
    }

    public void setTypeServices(List<TypeService> typeServices) {
        this.typeServices = typeServices;
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
