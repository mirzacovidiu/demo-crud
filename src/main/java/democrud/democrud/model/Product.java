package democrud.democrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import democrud.democrud.enums.CategoryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    private Long id;
    private String name;
    private String description;
    private Double price;


    @ManyToMany
    @JoinTable(name = "products_orders",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "orderId"))
    @JsonIgnore
    @ToString.Exclude
    private Set<Order> orderList;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
