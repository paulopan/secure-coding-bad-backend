package net.croz.owasp.badexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "auth_user")
public class AuthUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_id_generator")
    @SequenceGenerator(name = "auth_user_id_generator", sequenceName = "auth_user_id_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "security_question_one")
    private String securityQuestionOne;

    @Column(name = "security_question_two")
    private String securityQuestionTwo;

    @Column(name = "security_question_three")
    private String securityQuestionThree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestionOne() {
        return securityQuestionOne;
    }

    public void setSecurityQuestionOne(String securityQuestionOne) {
        this.securityQuestionOne = securityQuestionOne;
    }

    public String getSecurityQuestionTwo() {
        return securityQuestionTwo;
    }

    public void setSecurityQuestionTwo(String securityQuestionTwo) {
        this.securityQuestionTwo = securityQuestionTwo;
    }

    public String getSecurityQuestionThree() {
        return securityQuestionThree;
    }

    public void setSecurityQuestionThree(String securityQuestionThree) {
        this.securityQuestionThree = securityQuestionThree;
    }

}
