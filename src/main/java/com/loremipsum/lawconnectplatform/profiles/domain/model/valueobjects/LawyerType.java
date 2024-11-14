package com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects;

public enum LawyerType {
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

    LawyerType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static LawyerType fromId(int id) {
        for (LawyerType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid LawyerType id: " + id);
    }
}
