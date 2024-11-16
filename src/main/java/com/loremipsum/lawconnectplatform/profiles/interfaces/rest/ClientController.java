package com.loremipsum.lawconnectplatform.profiles.interfaces.rest;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementConsultationsMadeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementPaidServicesCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllClientsQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientIdByEmailQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientCommandService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.ClientResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.CreateClientResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Clients Endpoints")
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public ClientController(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource){
        var createClientCommand = CreateClientCommandFromResourceAssembler.ToCommandFromResource(resource);
        var client = clientCommandService.handle(createClientCommand);
        if(client.isEmpty()) return ResponseEntity.badRequest().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long clientId){
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping
    public ResponseEntity<List<ClientResource>> getAllClients(){
        var getAllClientsQuery = new GetAllClientsQuery();
        var clients = clientQueryService.handle(getAllClientsQuery);
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::ToResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientResources);
    }

    @PatchMapping("/IncrementConsultation")
    public ResponseEntity<ClientResource> incrementConsultation(@RequestParam Long clientId){
        clientCommandService.handle(new IncrementConsultationsMadeCommand(clientId));
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @PatchMapping("/IncrementPaid")
    public ResponseEntity<ClientResource> incrementPaid(@RequestParam Long clientId){
        clientCommandService.handle(new IncrementPaidServicesCommand(clientId));
        var getClientByIdQuery = new GetClientByIdQuery(clientId);
        var client = clientQueryService.handle(getClientByIdQuery);
        if(client.isEmpty()) return ResponseEntity.notFound().build();
        var clientResource = ClientResourceFromEntityAssembler.ToResourceFromEntity(client.get());
        return ResponseEntity.ok(clientResource);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Long> getClientByEmail(@PathVariable String email){
        var client = clientQueryService.handle(new GetClientIdByEmailQuery(email));
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
