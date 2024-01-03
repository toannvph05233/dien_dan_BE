package com.dien_dan.repository;

import com.dien_dan.model.RoomChat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IRoomChatRepo extends CrudRepository<RoomChat, Long> {
    @Query(nativeQuery = true,value = "SELECT rc.* FROM room_chat rc JOIN user_room ur1 ON rc.id = ur1.room_id \n" +
            "JOIN user_room ur2 ON rc.id = ur2.room_id WHERE ur1.user_id = :idUser1 AND ur2.user_id = :idUser2 and type='single'")
    RoomChat getRoomChat(@Param("idUser1") long id_User1, @Param("idUser2") long id_User2);
}
