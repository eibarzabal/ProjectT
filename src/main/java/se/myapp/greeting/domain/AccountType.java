package se.myapp.greeting.domain;

import java.util.Arrays;

public enum AccountType {

    PERSONAL("personal"), BUSINESS("business");

    private String desc;

    AccountType(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }

    public static AccountType getByDescription(String description) {
        return Arrays.asList(AccountType.values()).stream().filter(c -> c.getDescription().equalsIgnoreCase(description))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid AccountType["+description+"]"));
    }

    public static AccountType getByName(String name) {
        return Arrays.asList(AccountType.values()).stream().filter(c -> c.name().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid AccountType with name["+name+"]"));
    }

}
