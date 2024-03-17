package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "classSchool")
public class classSchool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_class")
    private int idClass;
    @ManyToOne
    @JoinColumn(name = "ma_lop")
    private lop lop;
    @Column(name = "star_day")
    private Date schoolYear;
    @Column(name = "end_day")
    private Date endDay;
    @Column(name = "so_tiet")
    private int soTiet;
    @Column(name = "phong_hoc")
    private String phongHoc;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "staff_id")
    private staff homeroomTeacher;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private kyHoc kyHoc;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private subjects subjects;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "lichHoc",
            joinColumns = @JoinColumn(name="id_class"),
            inverseJoinColumns = @JoinColumn(name ="idthoiGianHoc")
    )
    private Collection<thoiGianHoc> thoiGianHocs;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "notebook",
            joinColumns = @JoinColumn(name="id_class"),
            inverseJoinColumns = @JoinColumn(name ="username")
    )
    private Collection<user> users;

    public classSchool() {
    }

    public classSchool(int idClass, vn.iback.studentmanager.entity.lop lop, Date schoolYear, Date endDay, int soTiet, String phongHoc, staff homeroomTeacher, vn.iback.studentmanager.entity.kyHoc kyHoc, vn.iback.studentmanager.entity.subjects subjects, Collection<thoiGianHoc> thoiGianHocs, Collection<user> users) {
        this.idClass = idClass;
        this.lop = lop;
        this.schoolYear = schoolYear;
        this.endDay = endDay;
        this.soTiet = soTiet;
        this.phongHoc = phongHoc;
        this.homeroomTeacher = homeroomTeacher;
        this.kyHoc = kyHoc;
        this.subjects = subjects;
        this.thoiGianHocs = thoiGianHocs;
        this.users = users;
    }

    public Collection<thoiGianHoc> getThoiGianHocs() {
        return thoiGianHocs;
    }

    public void setThoiGianHocs(Collection<thoiGianHoc> thoiGianHocs) {
        this.thoiGianHocs = thoiGianHocs;
    }

    public Collection<user> getUsers() {
        return users;
    }

    public void setUsers(Collection<user> users) {
        this.users = users;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public vn.iback.studentmanager.entity.lop getLop() {
        return lop;
    }

    public void setLop(vn.iback.studentmanager.entity.lop lop) {
        this.lop = lop;
    }

    public Date getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Date schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public staff getHomeroomTeacher() {
        return homeroomTeacher;
    }

    public void setHomeroomTeacher(staff homeroomTeacher) {
        this.homeroomTeacher = homeroomTeacher;
    }

    public vn.iback.studentmanager.entity.kyHoc getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(vn.iback.studentmanager.entity.kyHoc kyHoc) {
        this.kyHoc = kyHoc;
    }

    public vn.iback.studentmanager.entity.subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(vn.iback.studentmanager.entity.subjects subjects) {
        this.subjects = subjects;
    }

}
