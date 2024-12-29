package com.dev.restLms.juhwi.MessageService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.restLms.entity.Message;
import com.dev.restLms.juhwi.MessageService.projection.Ch_M_MASR_Projection;

public interface Ch_M_MASR_Repository extends JpaRepository<Message, String>{
    Page<Ch_M_MASR_Projection> findBySenderSessionIdAndReceiverSessionId(String senderSessionId, String receiverSessionId, Pageable pageable);
}