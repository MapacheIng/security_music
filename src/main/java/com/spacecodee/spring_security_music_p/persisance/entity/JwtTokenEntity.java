package com.spacecodee.spring_security_music_p.persisance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "jwt_token", schema = "public", catalog = "music_project")
public class JwtTokenEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "jwt_token_id", nullable = false)
    private int jwtTokenId;

    @Column(name = "jwt_token_token", nullable = false, length = 2048)
    private String jwtTokenToken;

    @Column(name = "jwt_token_is_valid", nullable = false)
    private boolean jwtTokenIsValid;

    @Column(name = "jwt_token_expiry_date", nullable = false)
    private LocalDateTime jwtTokenExpiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user_s", nullable = false)
    private UserSEntity userSEntity;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        JwtTokenEntity that = (JwtTokenEntity) object;
        return jwtTokenId == that.jwtTokenId
                && jwtTokenIsValid == that.jwtTokenIsValid
                && Objects.equals(jwtTokenToken, that.jwtTokenToken)
                && Objects.equals(jwtTokenExpiryDate, that.jwtTokenExpiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwtTokenId, jwtTokenToken, jwtTokenIsValid, jwtTokenExpiryDate);
    }
}
