package com.loremipsum.lawconnectplatform.communication.interfaces.rest;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentCommandService;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentQueryService;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AppointmentResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.CreateAppointmentResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/Appointment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointment", description = "Appointments Endpoints")
public class AppointmentController {

    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public AppointmentController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointment = appointmentCommandService.handle(createAppointmentCommand);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var consultation = externalConsultationCommunicationService.getConsultationById(resource.consultationId());
        var consultationResource = externalConsultationCommunicationService.createConsultationResource(consultation.get());
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get(), consultationResource.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentByConsultationId(@PathVariable Long consultationId) {
        var getAppointmentByIdQuery = new GetAllAppointmentsByConsultationIdQuery(consultationId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var consultation = externalConsultationCommunicationService.getConsultationById(consultationId);
        var consultationResource = externalConsultationCommunicationService.createConsultationResource(consultation.get());
        var appointmentResource = appointment.stream()
                .map(
                        appointment1 -> AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment1, consultationResource.get())
                )
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResource);
    }
}
