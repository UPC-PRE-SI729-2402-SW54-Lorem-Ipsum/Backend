package com.loremipsum.lawconnectplatform.communication.domain.model.valueobjects;

public enum CommunicationStatus {
    PENDING(1),
    CANCELLED(2),
    COMPLETED(3);

    private final int id;

    CommunicationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CommunicationStatus fromId(int id) {
        for (CommunicationStatus status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching status for id " + id);
    }
}
