package com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects;

import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;

public enum ConsultationType {
    CIVIL(0),
    CRIMINAL(1),
    LABOR(2),
    FAMILY(3),
    CORPORATE(4),
    TAX(5),
    ADMINISTRATIVE(6),
    INTELLECTUAL_PROPERTY(7),
    ENVIRONMENTAL(8),
    INTERNATIONAL(9),
    HUMAN_RIGHTS(10);

    private final int id;

    ConsultationType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ConsultationType fromId(int id) {
        for (ConsultationType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Consultation type id: " + id);
    }
}
