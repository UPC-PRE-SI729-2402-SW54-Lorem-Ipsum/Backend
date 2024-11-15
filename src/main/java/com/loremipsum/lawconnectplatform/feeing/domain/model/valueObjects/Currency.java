package com.loremipsum.lawconnectplatform.feeing.domain.model.valueObjects;

import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;

public enum Currency {
    PEN(1),
    USD(2);

    private final int id;

    Currency(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Currency fromId(int id) {
        for (Currency type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid currency id: " + id);
    }
}
