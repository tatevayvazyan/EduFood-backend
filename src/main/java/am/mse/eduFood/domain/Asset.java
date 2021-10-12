package am.mse.eduFood.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="asset_id")
    private Long id;

    @Column(name="uri", unique = true)
    private String uri;

    @Column(name="name", unique = true)
    private String name;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name="food_id", nullable=false)
    private Food food;

}
