package am.mse.eduFood.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "edu_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @Column(name="total_price")
    private double totalPrice;

    @OneToOne
    private User user;

    @OneToMany
    @JoinTable(
        name="OrderFoods",
        joinColumns = @JoinColumn( name="order_id"),
        inverseJoinColumns = @JoinColumn( name="food_id")
    )
    private List<Food> foods;

}
