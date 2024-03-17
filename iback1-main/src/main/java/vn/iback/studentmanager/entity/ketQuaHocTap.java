package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "keu_qua_hoc_tap")
public class ketQuaHocTap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ket_qua_hoc_tap")
    private int id;
    @ManyToOne
    @JoinColumn(name = "username")
    private user user;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private kyHoc kyHoc;
    @Column(name = "diem_ren_luyen")
    private double diemRenLuyen;
    @Column(name = "diem_trung_binh")
    private double diemTrungBinh;
    @Column(name = "ket_qua_hoc_tap")
    private String ketQuaHocTap;

    public ketQuaHocTap() {
    }

    public ketQuaHocTap(int id, vn.iback.studentmanager.entity.user user, vn.iback.studentmanager.entity.kyHoc kyHoc, double diemRenLuyen, double diemTrungBinh, String ketQuaHocTap) {
        this.id = id;
        this.user = user;
        this.kyHoc = kyHoc;
        this.diemRenLuyen = diemRenLuyen;
        this.diemTrungBinh = diemTrungBinh;
        this.ketQuaHocTap = ketQuaHocTap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }

    public vn.iback.studentmanager.entity.kyHoc getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(vn.iback.studentmanager.entity.kyHoc kyHoc) {
        this.kyHoc = kyHoc;
    }

    public double getDiemRenLuyen() {
        return diemRenLuyen;
    }

    public void setDiemRenLuyen(double diemRenLuyen) {
        this.diemRenLuyen = diemRenLuyen;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getKetQuaHocTap() {
        return ketQuaHocTap;
    }

    public void setKetQuaHocTap(String ketQuaHocTap) {
        this.ketQuaHocTap = ketQuaHocTap;
    }
}
