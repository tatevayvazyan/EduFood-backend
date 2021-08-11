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
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="food_id")
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name= "description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="category")
    private String category;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinTable(
        name="FoodAssets",
        joinColumns = @JoinColumn( name="food_id"),
        inverseJoinColumns = @JoinColumn( name="asset_id")
    )
    private List<Asset> assets;

}
