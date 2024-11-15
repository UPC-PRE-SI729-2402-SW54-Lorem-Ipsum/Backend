package com.loremipsum.lawconnectplatform.communication.domain.model.valueobjects;

public enum SenderType {
    CLIENT(1),
    LAWYER(2);

    private final int id;

    SenderType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SenderType fromId(int id) {
        for (SenderType status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching status for id " + id);
    }
}
