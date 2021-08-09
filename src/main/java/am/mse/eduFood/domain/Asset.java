package am.mse.eduFood.domain;

import javax.persistence.*;

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="asset_id")
    private Long id;

    @Column(name="uri")
    private String uri;

}
