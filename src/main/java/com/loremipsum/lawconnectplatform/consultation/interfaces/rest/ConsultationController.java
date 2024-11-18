package com.loremipsum.lawconnectplatform.consultation.interfaces.rest;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.ApproveConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.DeleteConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.RejectConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByClientIdAndLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByClientIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.AddPaymentResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.CreateConsultationResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.ConsultationResourceFromEntityAssembler;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.CreateConsultationCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
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
    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;

    public ConsultationController(ConsultationCommandService consultationCommandService, ConsultationQueryService consultationQueryService, ExternalPaymentConsultationServices externalPaymentConsultationServices) {
        this.consultationCommandService = consultationCommandService;
        this.consultationQueryService = consultationQueryService;
        this.externalPaymentConsultationServices = externalPaymentConsultationServices;
    }

    @Operation(summary = "Create A New Consultation")
    @PostMapping
    public ResponseEntity<ConsultationResource> createConsultation(@RequestBody CreateConsultationResource resource){
        var createConsultationCommand = CreateConsultationCommandFromResourceAssembler.toCommandFromResource(resource);
        var consultationId = consultationCommandService.handle(createConsultationCommand);

        if (consultationId == 0L) return ResponseEntity.badRequest().build();
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.badRequest().build();

        var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(consultation.get().getPayments());

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get(), paymentsResource);
        return new ResponseEntity<>(consultationResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get A Consultation By Id")
    @GetMapping("/{consultationId}")
    public ResponseEntity<ConsultationResource> getConsultation(@PathVariable Long consultationId){
        var getConsultationByIdQuery = new GetConsultationByIdQuery(consultationId);
        var consultation = consultationQueryService.handle(getConsultationByIdQuery);

        if (consultation.isEmpty()) return ResponseEntity.notFound().build();

        var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(consultation.get().getPayments());

        var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation.get(), paymentsResource);
        return ResponseEntity.ok(consultationResource);
    }

    @Operation(summary = "Get All Consultations By Lawyer Id")
    @GetMapping("/lawyerId/{lawyerId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByLawyerId(@PathVariable Long lawyerId){
        var getAllConsultationsByLawyerIdQuery = new GetAllConsultationsByLawyerIdQuery(lawyerId);
        var consultations = consultationQueryService.handle(getAllConsultationsByLawyerIdQuery);

        var consultationResources = consultations.stream().map( consultation ->{
                    var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(consultation.getPayments());
                    return ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation, paymentsResource);
                }
        ).toList();
        return ResponseEntity.ok(consultationResources);
    }

    @Operation(summary = "Get All Consultations By Client Id")
    @GetMapping("/clientId/{clientId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByClientId(@PathVariable Long clientId){
        var getAllConsultationsByClientIdQuery = new GetAllConsultationsByClientIdQuery(clientId);
        var consultations = consultationQueryService.handle(getAllConsultationsByClientIdQuery);
        var consultationResources = consultations.stream().map(consultation -> {
            var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(consultation.getPayments());
            return ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation, paymentsResource);
        }).toList();
        return ResponseEntity.ok(consultationResources);
    }

    @Operation(summary = "Get All Consultations By Lawyer Id And Client Id")
    @GetMapping("/lawyerId/{lawyerId}/clientId/{clientId}")
    public ResponseEntity<List<ConsultationResource>> getAllConsultationsByLawyerIdAndClientId(@PathVariable Long lawyerId, @PathVariable Long clientId){
        var getAllConsultationsByLawyerIdQuery = new GetAllConsultationsByClientIdAndLawyerIdQuery(clientId, lawyerId);
        var consultations = consultationQueryService.handle(getAllConsultationsByLawyerIdQuery);
        var consultationResources = consultations.stream().map(consultation -> {
            var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(consultation.getPayments());
            return ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation, paymentsResource);
        }).toList();
        return ResponseEntity.ok(consultationResources);
    }

    @Operation(summary = "Delete A Consultation")
    @DeleteMapping("/{consultationId}")
    public ResponseEntity<?> deleteConsultation(@PathVariable Long consultationId){
        var deleteConsultationCommand = new DeleteConsultationCommand(consultationId);
        consultationCommandService.handle(deleteConsultationCommand);
        return ResponseEntity.ok("Consultation deleted successfully");
    }

    @Operation(summary = "Add A Payment To Consultation")
    @PostMapping("/payments")
    public ResponseEntity<?> addPaymentToConsultation(@RequestBody AddPaymentResource resource){
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        consultationCommandService.handle(createPaymentCommand);
        return ResponseEntity.ok("Payment added successfully");
    }

    @Operation(summary = "Approve A Consultation")
    @PatchMapping("/approve/{consultationId}")
    public ResponseEntity<?> approveConsultation(@PathVariable Long consultationId){
        consultationCommandService.handle(new ApproveConsultationCommand(consultationId));
        return ResponseEntity.ok("Consultation approved successfully");
    }

    @Operation(summary = "Reject A Consultation")
    @PatchMapping("/reject/{consultationId}")
    public ResponseEntity<?> declineConsultation(@PathVariable Long consultationId){
        consultationCommandService.handle(new RejectConsultationCommand(consultationId));
        return ResponseEntity.ok("Consultation approved successfully");
    }
}
