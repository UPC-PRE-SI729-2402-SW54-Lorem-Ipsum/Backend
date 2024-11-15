package com.loremipsum.lawconnectplatform.communication.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.AddMessageByChatRoomIdCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.valueobjects.SenderType;
import com.loremipsum.lawconnectplatform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class MessageItem extends AuditableModel {

    @Column(nullable = false)
    private String content;

    private boolean isRead;

    private SenderType senderType;

    @ManyToOne
    @JoinColumn(name = "chatRoom_id")
    @JsonIgnore
    private ChatRoom chatRoom;

    public MessageItem(AddMessageByChatRoomIdCommand command, ChatRoom chatRoom) {
        this.content = command.message();
        this.isRead = false;
        this.chatRoom = chatRoom;
        this.senderType = SenderType.fromId(command.sender());
    }

    public MessageItem() {

    }

    public void markAsRead() {
        this.isRead = true;
    }

}
