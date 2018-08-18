package com.sanctaultras.encryptedchat.user.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,UUID> {

    @Query("SELECT c FROM ChatRoom c left join fetch c.users u where ?1 = u.id")
    List<ChatRoom> findAllByUser_Id(String id);

    @Query("SELECT 1 from ChatRoom c join c.users u where ?2 in(u.id) and c.id = ?1")
    Integer isParticipating(UUID chatRoomId, String userId);

}