package tech.sujitjayaraj.crm.entity;

import lombok.Getter;

@Getter
public enum Role {
    OWNER("ROLE_OWNER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER"),
    EMPLOYEE("ROLE_EMPLOYEE");

    private final String name;

    Role(String name){
        this.name = name;
    }
}
