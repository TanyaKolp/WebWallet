package web.test.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tania on 23.04.17.
 */
@Entity
@Table(name = "ServicesSection")
public class ServicesSection extends Model {
    @Id
    @Column(name = "section_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_section")
    private String name;
    @Column(name = "all_worth")
    private Double allWorth;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "servicesSection")
    private List<TypeService> typeServices;

    public List<TypeService> getTypeServices() {
        return typeServices;
    }

    public void setTypeServices(List<TypeService> typeServices) {
        this.typeServices = typeServices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAllWorth() {
        return allWorth;
    }

    public void setAllWorth(Double allWorth) {
        this.allWorth = allWorth;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
