package web.test.model;

import javax.persistence.*;

/**
 * Created by tania on 23.04.17.
 */
@Entity
@Table(name = "Budget_head")
public class BudgetHead extends Model{
    @Id
    @Column(name = "budget_head_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "budget_type")
    private String type;
    @Column(name = "budget_title")
    private String title;
    @Column(name = "budget_amount")
    private Double amount;
    @Column(name = "budget_month")
    private Double month;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getMonth() {
        return month;
    }

    public void setMonth(Double month) {
        this.month = month;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
