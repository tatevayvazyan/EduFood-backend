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
@Table(name = "edu_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="class_number")
    private String classNumber;

    @Column(name="is_valid")
    private boolean isValid;

    @Enumerated(EnumType.STRING)
    private ERole role;

}
