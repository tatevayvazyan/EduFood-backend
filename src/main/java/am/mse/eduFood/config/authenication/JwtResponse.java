package am.mse.eduFood.config.authenication;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;

    private final String role;

    private final Long id;

    public JwtResponse(String jwttoken, String role, Long id) {

        this.jwttoken = jwttoken;
        this.role = role;
        this.id = id;
    }

    public String getToken() {

        return this.jwttoken;
    }

    public String getRole() {

        return this.role;
    }

    public Long getId() {

        return this.id;
    }

}