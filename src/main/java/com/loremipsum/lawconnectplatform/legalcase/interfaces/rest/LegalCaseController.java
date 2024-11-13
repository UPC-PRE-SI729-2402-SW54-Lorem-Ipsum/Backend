package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseCommandService;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseQueryService;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.CreateLegalCaseResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.LegalCaseResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.CreateLegalCaseCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.LegalCaseResourceFromEntityAssembler;
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

    public LegalCaseController(LegalCaseCommandService legalCaseCommandService, LegalCaseQueryService legalCaseQueryService) {
        this.legalCaseCommandService = legalCaseCommandService;
        this.legalCaseQueryService = legalCaseQueryService;
    }

    @PostMapping
    public ResponseEntity<LegalCaseResource> createLegalCase(@RequestBody CreateLegalCaseResource resource){
        var createLegalCaseCommand = CreateLegalCaseCommandFromResourceAssembler.toCommandFromResource(resource);
        var legalCase = legalCaseCommandService.handle(createLegalCaseCommand);
        if(legalCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var legalCaseResource = LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase.get());
        return new ResponseEntity<>(legalCaseResource, HttpStatus.CREATED);
    }
    @GetMapping("/{legalCaseId}")
    public ResponseEntity<LegalCaseResource> getLegalCaseById(@PathVariable Long legalCaseId){
        var getLegalCaseByIdQuery = new GetLegalCaseByIdQuery(legalCaseId);
        var legalCase = legalCaseQueryService.handle(getLegalCaseByIdQuery);
        if(legalCase.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var legalCaseResource = LegalCaseResourceFromEntityAssembler.toEntityFromResource(legalCase.get());
        return ResponseEntity.ok(legalCaseResource);
    }
    @GetMapping
    public ResponseEntity<List<LegalCaseResource>> getAllLegalCases(){
        var legalAllCasesQuery = legalCaseQueryService.handle(new GetAllLegalCasesQuery());
        var legalCaseResources = legalAllCasesQuery
                .stream()
                .map(LegalCaseResourceFromEntityAssembler::toEntityFromResource)
                .toList();
        return ResponseEntity.ok(legalCaseResources);
    }
    @PatchMapping("/close/{legalCaseId}")
    public ResponseEntity<?> closeLegalCase(@PathVariable Long legalCaseId){
        legalCaseCommandService.handle(new CloseLegalCaseCommand(legalCaseId));
        return ResponseEntity.ok("Legal Case Closed Successfully");
    }
}
