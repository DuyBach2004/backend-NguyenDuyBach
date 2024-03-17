package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diem")
public class diem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diem")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id")
    private notebook notebook;
    @ManyToOne
    @JoinColumn(name="username")
    private user user;
    @Column(name = "diem_chuyen_can")
    private double diemChuyenCan;
    @Column(name = "diem_giua_ki")
    private double diemGiuaKi;
    @Column(name = "diem_cuoi_ki")
    private double diemCuoiKi;
    @Column(name = "diem_tong_ket")
    private double diemTongKet;

    public diem() {
    }

    public diem(int id, vn.iback.studentmanager.entity.notebook notebook, vn.iback.studentmanager.entity.user user, double diemChuyenCan, double diemGiuaKi, double diemCuoiKi, double diemTongKet) {
        this.id = id;
        this.notebook = notebook;
        this.user = user;
        this.diemChuyenCan = diemChuyenCan;
        this.diemGiuaKi = diemGiuaKi;
        this.diemCuoiKi = diemCuoiKi;
        this.diemTongKet = diemTongKet;
    }

    public int getIdDiem() {
        return id;
    }

    public void setIdDiem(int id) {
        this.id = id;
    }

    public vn.iback.studentmanager.entity.notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(vn.iback.studentmanager.entity.notebook notebook) {
        this.notebook = notebook;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }

    public double getDiemChuyenCan() {
        return diemChuyenCan;
    }

    public void setDiemChuyenCan(double diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public double getDiemGiuaKi() {
        return diemGiuaKi;
    }

    public void setDiemGiuaKi(double diemGiuaKi) {
        this.diemGiuaKi = diemGiuaKi;
    }

    public double getDiemCuoiKi() {
        return diemCuoiKi;
    }

    public void setDiemCuoiKi(double diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }

    public double getDiemTongKet() {
        return diemTongKet;
    }

    public void setDiemTongKet(double diemTongKet) {
        this.diemTongKet = diemTongKet;
    }
}
