package com.loremipsum.lawconnectplatform.communication.interfaces.rest;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllVideoCallsByConsultationId;
import com.loremipsum.lawconnectplatform.communication.domain.services.VideoCallCommandService;
import com.loremipsum.lawconnectplatform.communication.domain.services.VideoCallQueryService;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.CreateVideoCallResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.VideoCallResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.CreateVideoCallCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.VideoCallResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/video_call", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Video Call", description = "Video Call Endpoints")
public class VideoCallController {

    private final VideoCallCommandService videoCallCommandService;
    private final VideoCallQueryService videoCallQueryService;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public VideoCallController(VideoCallCommandService videoCallCommandService, VideoCallQueryService videoCallQueryService, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.videoCallCommandService = videoCallCommandService;
        this.videoCallQueryService = videoCallQueryService;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @PostMapping
    public ResponseEntity<VideoCallResource> createVideoCall(@RequestBody CreateVideoCallResource resource) {
        var createVideoCallCommand = CreateVideoCallCommandFromResourceAssembler.toCommandFromResource(resource);
        var videoCall = videoCallCommandService.handle(createVideoCallCommand);
        if (videoCall.isEmpty()) return ResponseEntity.badRequest().build();
        var consultation = externalConsultationCommunicationService.getConsultationById(resource.consultationId());
        var consultationResource = externalConsultationCommunicationService.createConsultationResource(consultation.get());
        var videoCallResource = VideoCallResourceFromEntityAssembler.toResourceFromEntity(videoCall.get(), consultationResource.get());
        return new ResponseEntity<>(videoCallResource, HttpStatus.CREATED);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<List<VideoCallResource>> getVideoCallByConsultationId(@PathVariable Long consultationId) {
        var videoCall = videoCallQueryService.handle(new GetAllVideoCallsByConsultationId(consultationId));
        if (videoCall.isEmpty()) return ResponseEntity.notFound().build();
        var consultation = externalConsultationCommunicationService.getConsultationById(consultationId);
        var consultationResource = externalConsultationCommunicationService.createConsultationResource(consultation.get());
        var videoCallResource = videoCall.stream()
                .map(videoCall1 -> {
                    return VideoCallResourceFromEntityAssembler.toResourceFromEntity(videoCall1, consultationResource.get());
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(videoCallResource);
    }

}
