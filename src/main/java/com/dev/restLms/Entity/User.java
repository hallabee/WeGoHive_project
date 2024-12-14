package com.dev.restLms.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String sessionId;

    private String userId;
    private String userPw;
    private String userBirth;
    private String userEmail;
    private String userName;
    private String phoneNumber;
    private String nickname;
    private String pwCount;
    private String userInactivate;
    private String longTermDisconnection;
    private String pwChangeDate;
    private String currentConnection;
    private String socialKeyValue;
    private String unsubscribe;
    private String fileNo;
    private String seqNo;

    @PrePersist
    public void generateUUID() {
        if (sessionId == null) {
            sessionId = UUID.randomUUID().toString();
        }
    }
    
}
