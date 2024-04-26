package br.com.copadomorro.copadomorro.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeUserEnum {

    ADMIN ("admin", "Administrador"),
    SOCCER_TEAM ("soccer_team", "Time");

    private String type;
    private String description;

    TypeUserEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonCreator
    public static TypeUserEnum ofValue(String type) {
        if (type.equalsIgnoreCase("admin")) {
            return ADMIN;
        } else if (type.equalsIgnoreCase("soccer_team")) {
            return SOCCER_TEAM;
        } else {
            return null;
        }
    }
}
