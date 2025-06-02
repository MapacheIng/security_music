package com.spacecodee.spring_security_music_p.persisance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_s", schema = "public", catalog = "music_project")
public class UserSEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user_s", nullable = false)
    private int idUserS;

    @Column(name = "user_s_username", nullable = false, length = 20)
    private String userSUsername;

    @Column(name = "user_s_password", nullable = false, length = 250)
    private String userSPassword;

    @Column(name = "user_s_name", nullable = false, length = 50)
    private String userSName;

    @Column(name = "user_s_lastname", nullable = false, length = 50)
    private String userSLastname;

    @Column(name = "user_s_email", nullable = false, length = 100)
    private String userSEmail;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserSEntity that = (UserSEntity) object;
        return idUserS == that.idUserS
                && Objects.equals(userSUsername, that.userSUsername)
                && Objects.equals(userSPassword, that.userSPassword)
                && Objects.equals(userSName, that.userSName)
                && Objects.equals(userSLastname, that.userSLastname)
                && Objects.equals(userSEmail, that.userSEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserS, userSUsername, userSPassword, userSName, userSLastname, userSEmail);
    }
}
