package se.myapp.greeting.domain;

import java.util.Arrays;

public enum BusinessType {

    SMALL("small"), BIG("big");

    private String desc;

    BusinessType(String desc) {
        this.desc = desc;
    }

    public String getDescription() {
        return desc;
    }

    public static BusinessType getByDescription(String description) {
        return Arrays.asList(BusinessType.values()).stream().filter(c -> c.getDescription().equalsIgnoreCase(description))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid BusinessType["+description+"]"));
    }

    public static BusinessType getByName(String name) {
        return Arrays.asList(BusinessType.values()).stream().filter(c -> c.name().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid BusinessType with name["+name+"]"));
    }

}
