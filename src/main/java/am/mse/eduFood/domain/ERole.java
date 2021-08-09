package am.mse.eduFood.domain;

public enum ERole {

    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    String eRole;

    private ERole(String eRole) {
        this.eRole = eRole;
    }

    public String eRole() {
        return eRole;
    }
}
