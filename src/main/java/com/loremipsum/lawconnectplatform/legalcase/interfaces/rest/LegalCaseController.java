package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest;

import com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseCommandService;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseQueryService;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.CreateLegalCaseResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.LegalCaseResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.CreateLegalCaseCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.LegalCaseResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/legal_cases", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Legal Cases", description = "Legal Cases Endpoints")
public class LegalCaseController {

    private final LegalCaseCommandService legalCaseCommandService;
    private final LegalCaseQueryService legalCaseQueryService;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public LegalCaseController(LegalCaseCommandService legalCaseCommandService, LegalCaseQueryService legalCaseQueryService, ExternalConsultationLegalCaseService externalConsultationLegalCaseService) {
        this.legalCaseCommandService = legalCaseCommandService;
        this.legalCaseQueryService = legalCaseQueryService;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    @Operation(summary = "Create A Legal Case")
    @PostMapping
    public ResponseEntity<LegalCaseResource> createLegalCase(@RequestBody CreateLegalCaseResource resource){
        var createLegalCaseCommand = CreateLegalCaseCommandFromResourceAssembler.toCommandFromResource(resource);
        var legalCase = legalCaseCommandService.handle(createLegalCaseCommand);
        if(legalCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var consultationResource = externalConsultationLegalCaseService.createConsultationResource(legalCase.get().getConsultation());
        var legalCaseResource = LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase.get(), consultationResource.get());
        return new ResponseEntity<>(legalCaseResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get A Legal Case By Id")
    @GetMapping("/{legalCaseId}")
    public ResponseEntity<LegalCaseResource> getLegalCaseById(@PathVariable Long legalCaseId){
        var getLegalCaseByIdQuery = new GetLegalCaseByIdQuery(legalCaseId);
        var legalCase = legalCaseQueryService.handle(getLegalCaseByIdQuery);
        if(legalCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var consultationResource = externalConsultationLegalCaseService.createConsultationResource(legalCase.get().getConsultation());
        var legalCaseResource = LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase.get(), consultationResource.get());
        return ResponseEntity.ok(legalCaseResource);
    }

    @Operation(summary = "Get All Legal Cases")
    @GetMapping
    public ResponseEntity<List<LegalCaseResource>> getAllLegalCases(){
        var legalAllCasesQuery = legalCaseQueryService.handle(new GetAllLegalCasesQuery());
        var legalCaseResources = legalAllCasesQuery
                .stream()
                .map(legalCase -> {
                    var consultationResource = externalConsultationLegalCaseService.createConsultationResource(legalCase.getConsultation());
                    return LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase, consultationResource.get());
                })
                .toList();
        return ResponseEntity.ok(legalCaseResources);
    }

    @Operation(summary = "Close A Legal Case")
    @PatchMapping("/close/{legalCaseId}")
    public ResponseEntity<?> closeLegalCase(@PathVariable Long legalCaseId){
        legalCaseCommandService.handle(new CloseLegalCaseCommand(legalCaseId));
        return ResponseEntity.ok("Legal Case Closed Successfully");
    }

    @Operation(summary = "Get A Legal Case By Consultation Id")
    @GetMapping("/consultation/{consultationId}")
    public ResponseEntity<LegalCaseResource> getLegalCaseByConsultationId(@PathVariable Long consultationId){
        var getLegalCaseByConsultationIdQuery = new GetLegalCaseByConsultationIdQuery(consultationId);
        var legalCase = legalCaseQueryService.handle(getLegalCaseByConsultationIdQuery);
        if(legalCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var consultationResource = externalConsultationLegalCaseService.createConsultationResource(legalCase.get().getConsultation());
        var legalCaseResource = LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase.get(), consultationResource.get());
        return ResponseEntity.ok(legalCaseResource);
    }
}
