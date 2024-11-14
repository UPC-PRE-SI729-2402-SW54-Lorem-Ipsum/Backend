package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.DocumentsItemResource;

public class DocumentsItemResourceFromEntityAssembler {
    public static DocumentsItemResource toEntityFromResource(DocumentsItem entity){
        return new DocumentsItemResource(
            entity.getId(),
            entity.getTitle(),
            entity.getType().name(),
            entity.getDescription(),
            entity.getLegalCase().getId(),
            entity.getStatus().name()
        );
    }
}
