package com.loremipsum.lawconnectplatform.followup.interfaces.rest;

import com.loremipsum.lawconnectplatform.followup.domain.model.commands.DeleteNotificationCommand;
import com.loremipsum.lawconnectplatform.followup.domain.model.queries.GetAllNotificationByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.followup.domain.model.queries.GetAllNotificationsByClientIdQuery;
import com.loremipsum.lawconnectplatform.followup.domain.model.queries.GetNotificationByIdQuery;
import com.loremipsum.lawconnectplatform.followup.domain.services.NotificationCommandService;
import com.loremipsum.lawconnectplatform.followup.domain.services.NotificationQueryService;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources.CreateNotificationResource;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources.NotificationResource;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notifications Endpoints")
public class NotificationController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @Operation(summary = "Create A Notification")
    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        var notification = notificationCommandService.handle(createNotificationCommand);
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get A Notification By Id")
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @Operation(summary = "Get All Notifications By Consultation Id")
    @GetMapping("/legal-case/{consultationId}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationsByConsultationId(@PathVariable Long consultationId) {
        var notifications = notificationQueryService.handle(new GetAllNotificationByConsultationIdQuery(consultationId));
        var notificationsResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationsResources);
    }

    @Operation(summary = "Get All Notifications By Client Id")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationsByClientId(@PathVariable Long clientId) {
        var notifications = notificationQueryService.handle(new GetAllNotificationsByClientIdQuery(clientId));
        var notificationsResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationsResources);
    }

    @Operation(summary = "Delete A Notification")
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        notificationCommandService.handle(new DeleteNotificationCommand(notificationId));
        return ResponseEntity.ok("Notification deleted successfully");
    }

}
