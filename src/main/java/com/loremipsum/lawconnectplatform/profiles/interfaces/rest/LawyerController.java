package com.loremipsum.lawconnectplatform.profiles.interfaces.rest;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerTypeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllLawyersQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerIdByEmailQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerTypeByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerCommandService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.AddLawyerPricesResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.AddLawyerTypeResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.CreateLawyerResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.LawyerResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.AddLawyerPricesCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.AddLawyerTypeCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.CreateLawyerCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.LawyerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/lawyers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lawyer", description = "Lawyer Endpoints")
public class LawyerController {

    private final LawyerCommandService lawyerCommandService;
    private final LawyerQueryService lawyerQueryService;

    public LawyerController(LawyerCommandService lawyerCommandService, LawyerQueryService lawyerQueryService) {
        this.lawyerCommandService = lawyerCommandService;
        this.lawyerQueryService = lawyerQueryService;
    }

    @PostMapping
    public ResponseEntity<LawyerResource> createLawyer(@RequestBody CreateLawyerResource resource){
        var createLawyerCommand = CreateLawyerCommandFromResourceAssembler.ToCommandFromResource(resource);
        var lawyer = lawyerCommandService.handle(createLawyerCommand);
        if(lawyer.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var lawyerResource = LawyerResourceFromEntityAssembler.ToEntityFromResource(lawyer.get());
        return new ResponseEntity<>(lawyerResource, HttpStatus.CREATED);
    }

    @GetMapping("/Id/{lawyerId}")
    public ResponseEntity<LawyerResource> getLawyerById(@PathVariable Long lawyerId){
        var getLawyerByIdQuery = new GetLawyerByIdQuery(lawyerId);
        var lawyer = lawyerQueryService.handle(getLawyerByIdQuery);
        if(lawyer.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var lawyerResource = LawyerResourceFromEntityAssembler.ToEntityFromResource(lawyer.get());
        return ResponseEntity.ok(lawyerResource);
    }

    @GetMapping
    public ResponseEntity<List<LawyerResource>> getAllLawyers(){
        var GetAllLawyersQuery = new GetAllLawyersQuery();
        var lawyers = lawyerQueryService.handle(GetAllLawyersQuery);
        var lawyersResource = lawyers.stream()
                .map(LawyerResourceFromEntityAssembler::ToEntityFromResource)
                .toList();
        return ResponseEntity.ok(lawyersResource);
    }

    @PatchMapping("/prices")
    public ResponseEntity<LawyerResource> addLawyerPrice(@RequestBody AddLawyerPricesResource resource){
        var addLawyerPriceCommand = AddLawyerPricesCommandFromResourceAssembler.ToCommandFromResource(resource);
        lawyerCommandService.handle(addLawyerPriceCommand);
        var lawyer = lawyerQueryService.handle(new GetLawyerByIdQuery(resource.lawyerId()));
        var lawyerResource = LawyerResourceFromEntityAssembler.ToEntityFromResource(lawyer.get());
        return ResponseEntity.ok(lawyerResource);
    }

    @PatchMapping("/type")
    public ResponseEntity<LawyerResource> addLawyerType(@RequestBody AddLawyerTypeResource resource){
        var addLawyerTypeCommand = AddLawyerTypeCommandFromResourceAssembler.ToCommandFromResource(resource);
        lawyerCommandService.handle(addLawyerTypeCommand);
        var lawyer = lawyerQueryService.handle(new GetLawyerByIdQuery(resource.lawyerId()));
        var lawyerResource = LawyerResourceFromEntityAssembler.ToEntityFromResource(lawyer.get());
        return ResponseEntity.ok(lawyerResource);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Long> getLawyerByEmail(@PathVariable String email){
        var lawyer = lawyerQueryService.handle(new GetLawyerIdByEmailQuery(email));
        return lawyer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
