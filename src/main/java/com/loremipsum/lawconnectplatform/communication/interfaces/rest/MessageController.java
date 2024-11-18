package com.loremipsum.lawconnectplatform.communication.interfaces.rest;

import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllMessagesByChatRoomIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.MessageCommandService;
import com.loremipsum.lawconnectplatform.communication.domain.services.MessageQueryService;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AddMessageByChatRoomIdResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.MessageResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.AddMessageByChatRoomIdCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/message", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Message", description = "Message Endpoints")
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessageController(MessageCommandService messageCommandService, MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @Operation(summary = "Add A Message By Chat Room Id")
    @PostMapping
    public ResponseEntity<?> addMessageByChatRoomId(@RequestBody AddMessageByChatRoomIdResource resource) {
        var addMessageByChatRoomIdCommand = AddMessageByChatRoomIdCommandFromResourceAssembler.toCommandFromResource(resource);
        messageCommandService.handle(addMessageByChatRoomIdCommand);
        return ResponseEntity.ok("Message added successfully");
    }

    @Operation(summary = "Get All Messages By Chat Room Id")
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<MessageResource>> getAllMessageByChatRoomId(@PathVariable Long chatRoomId) {
        var message = messageQueryService.handle(new GetAllMessagesByChatRoomIdQuery(chatRoomId));
        if (message.isEmpty()) return ResponseEntity.notFound().build();
        var messageResource = message.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(messageResource);
    }
}
