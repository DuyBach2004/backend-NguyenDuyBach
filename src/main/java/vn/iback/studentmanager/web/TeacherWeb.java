package vn.iback.studentmanager.web;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class TeacherWeb {
    @NotNull(message = "thông tin bắt buộc")
    @Size(min=1, message = "độ dài tối thiểu là 1")
    private String fullName;
    @NotNull(message = "thông tin bắt buộc")
    private String gender;
    @NotNull(message = "thông tin bắt buộc")
    private String role;
    @Email
    @NotNull(message = "thông tin bắt buộc")
    private String email;
    @NotNull(message = "thông tin bắt buộc")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @NotNull(message = "thông tin bắt buộc")
    private Date dayIntoSchool;
    @NotNull(message = "thông tin bắt buộc")
    private Date birthday;

    public TeacherWeb() {
    }

    public TeacherWeb(String fullName, String gender, String role, String email, String phoneNumber, Date dayIntoSchool, Date birthday) {
        this.fullName = fullName;
        this.gender = gender;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dayIntoSchool = dayIntoSchool;
        this.birthday = birthday;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}
