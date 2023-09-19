package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
@Entity
public class User extends AbstractModel {
    @Column(nullable = false, unique = true)
    private String email;

    private String firstname;

    private String lastname;

    @Column
    private Integer age;

    @Column
    private String phoneNumber;

    @Column
    private Long idChatBot;

    @Enumerated(EnumType.STRING)
    private BotState botState;

    @Column(nullable = false)
    private Boolean isActive;

    private String code;
}
