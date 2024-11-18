package com.loremipsum.lawconnectplatform.communication.interfaces.rest;

import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentCommandService;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentQueryService;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AppointmentResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.CreateAppointmentResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
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

    public AppointmentController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @Operation(summary = "Create Appointment")
    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var appointment = appointmentCommandService.handle(createAppointmentCommand);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Appointment By Consultation Id")
    @GetMapping("/{consultationId}")
    public ResponseEntity<List<AppointmentResource>> getAppointmentByConsultationId(@PathVariable Long consultationId) {
        var getAppointmentByIdQuery = new GetAllAppointmentsByConsultationIdQuery(consultationId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.notFound().build();
        var appointmentResource = appointment.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResource);
    }
}
