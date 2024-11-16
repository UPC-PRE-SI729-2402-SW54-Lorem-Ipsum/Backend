package com.loremipsum.lawconnectplatform.consultation.interfaces.rest;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.DeleteConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.AddPaymentResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.CreateConsultationResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.ConsultationResourceFromEntityAssembler;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.CreateConsultationCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/consultation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Consultations", description = "Consultation Management Endpoints")
public class ConsultationController {

    private final ConsultationCommandService consultationCommandService;
    private final ConsultationQueryService consultationQueryService;

    public ConsultationController(ConsultationCommandService consultationCommandService, ConsultationQueryService consultationQueryService) {
        this.consultationCommandService = consultationCommandService;
        this.consultationQueryService = consultationQueryService;
    }

    @PostMapping
    public ResponseEntity<ConsultationResource> createConsultation(@RequestBody CreateConsultationResource resource){
        var createConsultationCommand = CreateConsultationCommandFromResourceAssembler.toCommandFromResource(resource);
        var consultationId = consultationCommandService.handle(createConsultationCommand);

        if (consultationId == 0L) return ResponseEntity.badRequest().build();
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.badRequest().build();

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get());
        return new ResponseEntity<>(consultationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<ConsultationResource> getConsultation(@PathVariable Long consultationId){
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.notFound().build();

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get());
        return ResponseEntity.ok(consultationResource);
    }

    @GetMapping("/lawyerId/{lawyerId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByLawyerId(@PathVariable Long lawyerId){
        var lawyer = new LawyerC(lawyerId);
        var getAllConsultationsByLawyerIdQuery = new GetAllConsultationsByLawyerIdQuery(lawyer);
        var consultations = consultationQueryService.handle(getAllConsultationsByLawyerIdQuery);
        var consultationResources = consultations.stream().map(ConsultationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(consultationResources);
    }

    @DeleteMapping("/{consultationId}")
    public ResponseEntity<?> deleteConsultation(@PathVariable Long consultationId){
        var deleteConsultationCommand = new DeleteConsultationCommand(consultationId);
        consultationCommandService.handle(deleteConsultationCommand);
        return ResponseEntity.ok("Consultation deleted successfully");
    }

    @PostMapping("/payments")
    public ResponseEntity<?> addPaymentToConsultation(@RequestBody AddPaymentResource resource){
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        consultationCommandService.handle(createPaymentCommand);
        return ResponseEntity.ok("Payment added successfully");
    }
}
