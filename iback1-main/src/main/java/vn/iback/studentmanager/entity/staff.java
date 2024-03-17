package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "staff")
public class staff {
    @Id
    @Column(name = "staff_id")
    private String staff_id;
    @Column(name="gender",length = 50)
    private String gender;
    @Column(name="role",length = 100)
    private String role;
    @Column(name="full_name",length = 50)
    private String fullName;
    @Column(name="email",length = 50)
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="day_into_school")
    private Date dayIntoSchool;
    @Column(name="birthday")
    private Date birthday;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ma_khoa")
    private khoa maKhoa;

    public staff() {
    }

    public staff(String staff_id, String gender, String role, String fullName, String email, String phoneNumber, Date dayIntoSchool, Date birthday, khoa maKhoa) {
        this.staff_id = staff_id;
        this.gender = gender;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dayIntoSchool = dayIntoSchool;
        this.birthday = birthday;
        this.maKhoa = maKhoa;
    }

    public khoa getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(khoa maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDayIntoSchool() {
        return dayIntoSchool;
    }

    public void setDayIntoSchool(Date dayIntoSchool) {
        this.dayIntoSchool = dayIntoSchool;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
