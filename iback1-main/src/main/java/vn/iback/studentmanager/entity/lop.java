package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lop")
public class lop {
    @Id
    @Column(name = "ma_lop")
    private String maLop;
    @Column(name = "ten_lop")
    private String tenLop;
    @ManyToOne
    @JoinColumn(name = "ma_khoa")
    private khoa khoa;
    @ManyToOne
    @JoinColumn(name = "specialized_code")
    private specialization specialization;
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private staff staff;

    public lop() {
    }

    public lop(String maLop, String tenLop, vn.iback.studentmanager.entity.khoa khoa, vn.iback.studentmanager.entity.specialization specialization, vn.iback.studentmanager.entity.staff staff) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.khoa = khoa;
        this.specialization = specialization;
        this.staff = staff;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public vn.iback.studentmanager.entity.khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(vn.iback.studentmanager.entity.khoa khoa) {
        this.khoa = khoa;
    }

    public vn.iback.studentmanager.entity.specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(vn.iback.studentmanager.entity.specialization specialization) {
        this.specialization = specialization;
    }

    public vn.iback.studentmanager.entity.staff getStaff() {
        return staff;
    }

    public void setStaff(vn.iback.studentmanager.entity.staff staff) {
        this.staff = staff;
    }
}
