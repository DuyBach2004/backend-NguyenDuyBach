package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.util.Collection;
@Entity
@Table(name = "user")
public class user {
    @Id
    @Column(name="username")
    private String username;
    @Column(name="password", length = 256)
    private String password;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private student studentId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "parents_id")
    private parents parentsId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ma_khoa")
    private khoa khoa;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ma_lop")
    private lop lop;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name = "location")
    private String location;
    @Column(name="email")
    private String email;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "specialized_code")
    private specialization specialization;
    @Lob
    @Column(name="avatar")
    private String avatar;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_username"),
            inverseJoinColumns = @JoinColumn(name ="roles_id")
    )
    private Collection<roles> roles;
    @Column(name = "trang_thai_xac_thuc")
    private boolean trangThaiXacThuc;
    @Column(name = "maxacthuc")
    private String maXacThuc;
    @Column(name = "thoigianhieuluc")
    private Date thoiGianHieuLucCuaMaXacThuc;


    public user() {
    }


    public user(String username, String password, student studentId, parents parentsId, vn.iback.studentmanager.entity.khoa khoa, vn.iback.studentmanager.entity.lop lop, String firstname, String lastname, String location, String email, vn.iback.studentmanager.entity.specialization specialization, String avatar, Collection<vn.iback.studentmanager.entity.roles> roles, boolean trangThaiXacThuc, String maXacThuc, Date thoiGianHieuLucCuaMaXacThuc) {
        this.username = username;
        this.password = password;
        this.studentId = studentId;
        this.parentsId = parentsId;
        this.khoa = khoa;
        this.lop = lop;
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.email = email;
        this.specialization = specialization;
        this.avatar = avatar;
        this.roles = roles;
        this.trangThaiXacThuc = trangThaiXacThuc;
        this.maXacThuc = maXacThuc;
        this.thoiGianHieuLucCuaMaXacThuc = thoiGianHieuLucCuaMaXacThuc;
    }

    public Date getThoiGianHieuLucCuaMaXacThuc() {
        return thoiGianHieuLucCuaMaXacThuc;
    }

    public void setThoiGianHieuLucCuaMaXacThuc(Date thoiGianHieuLucCuaMaXacThuc) {
        this.thoiGianHieuLucCuaMaXacThuc = thoiGianHieuLucCuaMaXacThuc;
    }

    public String getMaXacThuc() {
        return maXacThuc;
    }

    public void setMaXacThuc(String maXacThuc) {
        this.maXacThuc = maXacThuc;
    }

    public boolean isTrangThaiXacThuc() {
        return trangThaiXacThuc;
    }

    public void setTrangThaiXacThuc(boolean trangThaiXacThuc) {
        this.trangThaiXacThuc = trangThaiXacThuc;
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

    public student getStudentId() {
        return studentId;
    }

    public void setStudentId(student studentId) {
        this.studentId = studentId;
    }

    public parents getParentsId() {
        return parentsId;
    }

    public void setParentsId(parents parentsId) {
        this.parentsId = parentsId;
    }

    public vn.iback.studentmanager.entity.khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(vn.iback.studentmanager.entity.khoa khoa) {
        this.khoa = khoa;
    }

    public vn.iback.studentmanager.entity.lop getLop() {
        return lop;
    }

    public void setLop(vn.iback.studentmanager.entity.lop lop) {
        this.lop = lop;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public vn.iback.studentmanager.entity.specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(vn.iback.studentmanager.entity.specialization specialization) {
        this.specialization = specialization;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<vn.iback.studentmanager.entity.roles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<vn.iback.studentmanager.entity.roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return username;
    }
}
