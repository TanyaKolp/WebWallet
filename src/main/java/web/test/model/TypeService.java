package web.test.model;

import javax.persistence.*;

/**
 * Created by tania on 23.04.17.
 */
@Entity
@Table(name = "TypeService")
public class TypeService extends Model {
    @Id
    @Column(name = "typeService_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "all_worth")
    private Double worth;
    @Column(name = "name_type_service")
    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private ServicesSection servicesSection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWorth() {
        return worth;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServicesSection getServicesSection() {
        return servicesSection;
    }

    public void setServicesSection(ServicesSection servicesSection) {
        this.servicesSection = servicesSection;
    }
}
