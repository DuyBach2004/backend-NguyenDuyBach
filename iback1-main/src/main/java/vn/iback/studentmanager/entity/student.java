package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "student")
public class student {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "day_into_school")
    private Date dayIntoSchool;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "diem_tich_luy")
    private double diemTichLuy;
    @Column(name = "diem_tb_hocKy")
    private double diemTbHocKy;
    @ManyToOne
    @JoinColumn(name = "parents_id")
    private parents parents;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private classSchool classSchool;

    @Column(name = "dan_toc")
    private String dantoc;
    @Column(name = "ton_giao")
    private String tongiao;
    @Column(name = "gioi_tinh")
    private String gioitinh;
    @Column(name = "quoc_tich")
    private String quoctich;
    @Column(name = "can_cuoc_cong_dan")
    private String cccd;
    @Column(name = "ngay_cap")
    private Date ngaycap;
    @Column(name = "noi_cap")
    private String noicap;
    @Column(name = "so_dien_thoai")
    private String sodienthoai;
    @Column(name = "ngay_vao_doan")
    private Date ngayvaodoan;
    @Column(name = "ngay_vao_dang")
    private Date ngayvaodang;
    @Column(name = "tinh_thanh")
    private String tinhthanh;
    @Column(name = "huyen_quan")
    private String huyenquan;
    @Column(name = "xa_phuong")
    private String xa_phuong;



    public student() {
    }

    public student(String studentId) {
        this.studentId = studentId;
    }

    public student(String studentId, String fullName, Date dayIntoSchool, Date birthday, double diemTichLuy, double diemTbHocKy, vn.iback.studentmanager.entity.parents parents, vn.iback.studentmanager.entity.classSchool classSchool) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dayIntoSchool = dayIntoSchool;
        this.birthday = birthday;
        this.diemTichLuy = diemTichLuy;
        this.diemTbHocKy = diemTbHocKy;
        this.parents = parents;
        this.classSchool = classSchool;
    }

    public student(String studentId, String fullName, Date dayIntoSchool, Date birthday, double diemTichLuy, double diemTbHocKy, vn.iback.studentmanager.entity.parents parents, vn.iback.studentmanager.entity.classSchool classSchool, String dantoc, String tongiao, String gioitinh, String quoctich, String cccd, Date ngaycap, String noicap, String sodienthoai, Date ngayvaodoan, Date ngayvaodang, String tinhthanh, String huyenquan, String xa_phuong) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dayIntoSchool = dayIntoSchool;
        this.birthday = birthday;
        this.diemTichLuy = diemTichLuy;
        this.diemTbHocKy = diemTbHocKy;
        this.parents = parents;
        this.classSchool = classSchool;
        this.dantoc = dantoc;
        this.tongiao = tongiao;
        this.gioitinh = gioitinh;
        this.quoctich = quoctich;
        this.cccd = cccd;
        this.ngaycap = ngaycap;
        this.noicap = noicap;
        this.sodienthoai = sodienthoai;
        this.ngayvaodoan = ngayvaodoan;
        this.ngayvaodang = ngayvaodang;
        this.tinhthanh = tinhthanh;
        this.huyenquan = huyenquan;
        this.xa_phuong = xa_phuong;
    }

    public double getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(double diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public double getDiemTbHocKy() {
        return diemTbHocKy;
    }

    public void setDiemTbHocKy(double diemTbHocKy) {
        this.diemTbHocKy = diemTbHocKy;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public vn.iback.studentmanager.entity.parents getParents() {
        return parents;
    }

    public void setParents(vn.iback.studentmanager.entity.parents parents) {
        this.parents = parents;
    }

    public vn.iback.studentmanager.entity.classSchool getClassSchool() {
        return classSchool;
    }

    public void setClassSchool(vn.iback.studentmanager.entity.classSchool classSchool) {
        this.classSchool = classSchool;
    }

    public String getDantoc() {
        return dantoc;
    }

    public void setDantoc(String dantoc) {
        this.dantoc = dantoc;
    }

    public String getTongiao() {
        return tongiao;
    }

    public void setTongiao(String tongiao) {
        this.tongiao = tongiao;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Date getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(Date ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public Date getNgayvaodoan() {
        return ngayvaodoan;
    }

    public void setNgayvaodoan(Date ngayvaodoan) {
        this.ngayvaodoan = ngayvaodoan;
    }

    public Date getNgayvaodang() {
        return ngayvaodang;
    }

    public void setNgayvaodang(Date ngayvaodang) {
        this.ngayvaodang = ngayvaodang;
    }

    public String getTinhthanh() {
        return tinhthanh;
    }

    public void setTinhthanh(String tinhthanh) {
        this.tinhthanh = tinhthanh;
    }

    public String getHuyenquan() {
        return huyenquan;
    }

    public void setHuyenquan(String huyenquan) {
        this.huyenquan = huyenquan;
    }

    public String getXa_phuong() {
        return xa_phuong;
    }

    public void setXa_phuong(String xa_phuong) {
        this.xa_phuong = xa_phuong;
    }
}
