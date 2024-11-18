package com.loremipsum.lawconnectplatform.communication.interfaces.rest;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateChatRoomCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetChatRoomByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.ChatRoomCommandService;
import com.loremipsum.lawconnectplatform.communication.domain.services.ChatRoomQueryService;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.ChatRoomResource;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform.ChatRoomResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/ChatRoom", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ChatRoom", description = "ChatRoom Endpoints")
public class ChatRoomController {

    private final ChatRoomCommandService chatRoomCommandService;
    private final ChatRoomQueryService chatRoomQueryService;

    public ChatRoomController(ChatRoomCommandService chatRoomCommandService, ChatRoomQueryService chatRoomQueryService) {
        this.chatRoomCommandService = chatRoomCommandService;
        this.chatRoomQueryService = chatRoomQueryService;
    }

    @Operation(summary = "Create A Chat Room By Consultation Id")
    @PostMapping("/{consultationId}")
    public ResponseEntity<ChatRoomResource> createChatRoom(@PathVariable Long consultationId) {
        var createChatRoomCommand = new CreateChatRoomCommand(consultationId);
        var chatRoom = chatRoomCommandService.handle(createChatRoomCommand);
        if (chatRoom.isEmpty()) return ResponseEntity.badRequest().build();
        var chatRoomResource = ChatRoomResourceFromEntityAssembler.toResourceFromEntity(chatRoom.get());
        return new ResponseEntity<>(chatRoomResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Chat Room By Consultation Id")
    @GetMapping("/{consultationId}")
    public ResponseEntity<ChatRoomResource> getChatRoomByConsultationId(@PathVariable Long consultationId) {
        var chatRoom = chatRoomQueryService.handle(new GetChatRoomByConsultationIdQuery(consultationId));
        if (chatRoom.isEmpty()) return ResponseEntity.notFound().build();
        var chatRoomResource = ChatRoomResourceFromEntityAssembler.toResourceFromEntity(chatRoom.get());
        return ResponseEntity.ok(chatRoomResource);
    }
}
