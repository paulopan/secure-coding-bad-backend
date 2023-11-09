package net.croz.owasp.badexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id_generator")
    @SequenceGenerator(name = "session_id_generator", sequenceName = "session_id_seq",
        allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    private AuthUser authUser;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

}
