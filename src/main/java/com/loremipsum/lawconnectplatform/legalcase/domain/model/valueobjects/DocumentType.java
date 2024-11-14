package com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects;

public enum DocumentType {
    ACUERDO(0),
    ACUERDO_DE_CONSEJO_DIRECTIVO(1),
    CASACION(2),
    CONTRATO(3),
    CONVENIO(4),
    DECISION(5),
    DECRETO_DE_URGENCIA(6),
    DECRETO_LEGISLATIVO(7),
    DECRETO_LEY(8),
    DECRETO_SUPREMO(9),
    DIRECTIVA(10),
    EXPEDIENTE(11),
    RESOLUCION(12),
    RESOLUCION_ADMINISTRATIVA(13),
    RESOLUCION_DE_CONSEJO_DIRECTIVO(14),
    RESOLUCION_DE_CONTRALORIA(15),
    RESOLUCION_DE_GERENCIA(16),
    RESOLUCION_DE_GERENCIA_GENERAL(17),
    RESOLUCION_DE_PRESIDENCIA(18),
    RESOLUCION_DE_PRESIDENCIA_DEL_CONSEJO_DIRECTIVO(19),
    RESOLUCION_DE_PRESIDENCIA_EJECUTIVA(20),
    RESOLUCION_DE_SECRETARIA_EJECUTIVA(21),
    RESOLUCION_DE_SUPERINTENDENCIA(22),
    RESOLUCION_DIRECTORAL(23),
    RESOLUCION_JEFATURAL(24),
    RESOLUCION_MINISTERIAL(25),
    RESOLUCION_SUPREMA(26);

    private final int id;

    DocumentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DocumentType fromId(int id) {
        for (DocumentType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid DocumentType id: " + id);
    }
}