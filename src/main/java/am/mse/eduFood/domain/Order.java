package am.mse.eduFood.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(cascade = {CascadeType.ALL},  targetEntity = OrderItem.class)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItem> orderItems;

}
