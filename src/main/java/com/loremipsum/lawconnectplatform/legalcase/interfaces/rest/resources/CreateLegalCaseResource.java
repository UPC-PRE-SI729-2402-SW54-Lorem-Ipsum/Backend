package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources;

public record CreateLegalCaseResource (
        String title,
        String description,
        Long consultationId
){
}
