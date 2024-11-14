package com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects;

public enum DocumentsStatus {
    PENDING(1),
    COMPLETED(2),
    DENIED(3),
    IN_REVIEW(4);

    private final int id;

    DocumentsStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DocumentsStatus fromId(int id) {
        for (DocumentsStatus type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Document Status id: " + id);
    }
}
