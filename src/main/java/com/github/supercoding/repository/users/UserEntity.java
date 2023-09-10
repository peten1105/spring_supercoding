package com.github.supercoding.repository.users;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="userId")
@Builder
@Entity
@Table(name ="users")
public class UserEntity {
    @Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "user_name", length = 20)
    private String userName;
    @Column(name = "like_travel_place", length = 30)
    private String likeTravelPlace;
    @Column(name = "phone_num", length = 30)
    private String phoneNum;
}
