package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "student_card")
public class student_card{
    @Id
    @Column(name = "card_id")
    private String cardId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private student student;
    @Column(name = "avatar")
    private Blob avatar;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private int phoneNumber;

    public student_card() {
    }

    public student_card(String cardId, vn.iback.studentmanager.entity.student student, Blob avatar, String email, int phoneNumber) {
        this.cardId = cardId;
        this.student = student;
        this.avatar = avatar;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public vn.iback.studentmanager.entity.student getStudent() {
        return student;
    }

    public void setStudent(vn.iback.studentmanager.entity.student student) {
        this.student = student;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
