package am.mse.eduFood.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="food_id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name= "description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="category")
    private String category;

    @OneToMany
    @JoinTable(
        name="FoodAssets",
        joinColumns = @JoinColumn( name="food_id"),
        inverseJoinColumns = @JoinColumn( name="asset_id")
    )
    private List<Asset> assets;

}
