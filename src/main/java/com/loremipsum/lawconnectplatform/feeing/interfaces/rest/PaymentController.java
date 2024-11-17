package com.loremipsum.lawconnectplatform.feeing.interfaces.rest;

import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetAllPaymentByClientIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetPaymentByIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentCommandService;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentQueryService;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.CompletePaymentResource;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.transform.CompletePaymentCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api/payments",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payment", description = "Project Payments Management Endpoints")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(
            PaymentCommandService paymentCommandService,
            PaymentQueryService paymentQueryService
    ) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @Operation(summary = "Complete consultation Payment")
    @PatchMapping(value = "/consultation/{paymentId}")
    public ResponseEntity<PaymentResource> completePayment(@RequestBody CompletePaymentResource resource, @PathVariable Long paymentId) {
        var command = CompletePaymentCommandFromResourceAssembler.toCommandFromResource(resource, paymentId);
        var payment = paymentCommandService.handle(command);
        if (payment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @Operation(summary = "Get Payment Id By Consultation Id")
    @GetMapping(value = "/consultation/{consultationId}")
    public ResponseEntity<List<PaymentResource>> getAllPaymentsByConsultationId(@PathVariable Long consultationId) {
        var payment = paymentQueryService.handle(new GetAllPaymentsByConsultationIdQuery(consultationId));
        if (payment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var paymentResource = payment.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentResource);
    }

    @Operation(summary = "Get Payment By Id")
    @GetMapping(value = "/{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {
        var payment = paymentQueryService.handle(new GetPaymentByIdQuery(paymentId));
        if (payment.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(payment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @Operation(summary = "Get Payments By Client Id")
    @GetMapping(value = "/payment/{clientId}")
    public ResponseEntity<List<PaymentResource>> getAllPaymentsByDeveloper(@PathVariable Long clientId) {
        var payments = paymentQueryService.handle(new GetAllPaymentByClientIdQuery(clientId));
        var paymentsResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentsResources);
    }
}
