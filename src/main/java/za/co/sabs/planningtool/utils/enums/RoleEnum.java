package za.co.sabs.planningtool.utils.enums;

public enum RoleEnum {
    SYSADMIN("SYSADMIN"), CLIENT("CLIENT"), SUPPORT("SUPPORT"), USER("USER");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
