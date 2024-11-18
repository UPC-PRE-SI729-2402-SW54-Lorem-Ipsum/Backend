package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.ChangeDocumentStatusCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllDocumentsByLegalCaseQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetDocumentByIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.DocumentsCommandService;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.DocumentsQueryService;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.AddDocumentByLegalCaseIdResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.DocumentsItemResource;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.AddDocumentByLegalCaseIdCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform.DocumentsItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/documents", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Documents", description = "Documents Endpoints")
public class DocumentsItemController {

    private final DocumentsCommandService documentsCommandService;
    private final DocumentsQueryService documentsQueryService;

    public DocumentsItemController(DocumentsCommandService documentsCommandService, DocumentsQueryService documentsQueryService) {
        this.documentsCommandService = documentsCommandService;
        this.documentsQueryService = documentsQueryService;
    }

    @Operation(summary = "Add A Document")
    @PostMapping
    public ResponseEntity<?> addDocumentItem(@RequestBody AddDocumentByLegalCaseIdResource resource) {
        var addDocumentItemCommand = AddDocumentByLegalCaseIdCommandFromResourceAssembler.toCommandFromResource(resource);
        documentsCommandService.handle(addDocumentItemCommand);
        return ResponseEntity.ok("Document added successfully");
    }

    @Operation(summary = "Get A Document By Id")
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentsItemResource> getDocumentItemById(@PathVariable Long documentId) {
        var getDocumentItemByIdQuery = new GetDocumentByIdQuery(documentId);
        var documentItem = documentsQueryService.handle(getDocumentItemByIdQuery);
        if (documentItem.isEmpty()) return ResponseEntity.notFound().build();
        var documentItemResource = DocumentsItemResourceFromEntityAssembler.toEntityFromResource(documentItem.get());
        return ResponseEntity.ok(documentItemResource);
    }

    @Operation(summary = "Get All Documents By Legal Case Id")
    @GetMapping
    public ResponseEntity<List<DocumentsItemResource>> getAllDocumentItemsByLegalCaseId(@RequestParam Long legalCaseId) {
        var getAllDocumentItemsByLegalCaseIdQuery = new GetAllDocumentsByLegalCaseQuery(legalCaseId);
        var documentItems = documentsQueryService.handle(getAllDocumentItemsByLegalCaseIdQuery);
        var documentItemResources = documentItems.stream()
                .map(DocumentsItemResourceFromEntityAssembler::toEntityFromResource)
                .toList();
        return ResponseEntity.ok(documentItemResources);
    }

    @Operation(summary = "Change Document Status")
    @PatchMapping("/status/{documentId}")
    public ResponseEntity<?> changeDocumentStatus(@PathVariable Long documentId, @RequestParam Integer status) {
        documentsCommandService.handle(new ChangeDocumentStatusCommand(documentId, status));
        return ResponseEntity.ok("Document status changed successfully");
    }
}
