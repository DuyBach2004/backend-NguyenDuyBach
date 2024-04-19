package vn.iback.studentmanager.dto;

import vn.iback.studentmanager.entity.roles;
import vn.iback.studentmanager.entity.specialization;
import vn.iback.studentmanager.entity.student;

import java.util.Collection;

public class userDTO {
    private String username;
    private String fullName;
    private String studentId;
    private String khoa;
    private String email;
    private String specialization;
    private Collection<roles> roles;

    public userDTO(String username, String fullName, String studentId, String khoa, String email, String specialization, Collection<roles> role) {
        this.username = username;
        this.fullName = fullName;
        this.studentId = studentId;
        this.khoa = khoa;
        this.email = email;
        this.specialization = specialization;
        this.roles = role;
    }

    public userDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Collection<roles> getRole() {
        return roles;
    }

    public void setRole(Collection<roles> role) {
        this.roles = role;
    }
}
