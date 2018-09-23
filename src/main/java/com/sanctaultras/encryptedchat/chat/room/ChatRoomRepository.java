package com.sanctaultras.encryptedchat.chat.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,UUID> {

    @Query("SELECT u.userRooms FROM User u where u.id=?1")
    List<ChatRoom> findAllByUser_Id(Long id);

    @Query("SELECT 1 from ChatRoom c join c.users u where ?2 in(u.id) and c.id = ?1")
    Integer isParticipating(UUID chatRoomId, Long userId);

}