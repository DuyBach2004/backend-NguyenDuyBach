package vn.iback.studentmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "parents")
public class parents {
    @Id
    @Column(name = "parents_id", length = 50)
    private String parentsId;
    @Column(name = "phone_number")
    private int phone_number;
    @Column(name = "email")
    private String email;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "birthday")
    private Date birthday;

    public parents() {
    }

    public parents(String parentsId, int phone_number, String email, String fullname, Date birthday) {
        this.parentsId = parentsId;
        this.phone_number = phone_number;
        this.email = email;
        this.fullname = fullname;
        this.birthday = birthday;
    }

    public String getParentsId() {
        return parentsId;
    }

    public void setParentsId(String parentsId) {
        this.parentsId = parentsId;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
