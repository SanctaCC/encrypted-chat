package com.sanctaultras.encryptedchat.user.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    @Query("SELECT u.userRooms FROM User u where u.id=?1")
    List<ChatRoom> findAllByUser_Id(Long id);
}
