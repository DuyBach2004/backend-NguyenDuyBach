package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "thoiGianHoc")
public class thoiGianHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idthoiGianHoc")
    private int idThoiGianHoc;
    @Column(name = "tietHoc")
    private String tietHoc;
    @Column(name = "thuTrongTuan")
    private String thuTrongTuan;

    public thoiGianHoc() {
    }

    public thoiGianHoc(int idThoiGianHoc, String tietHoc, String thuTrongTuan) {
        this.idThoiGianHoc = idThoiGianHoc;
        this.tietHoc = tietHoc;
        this.thuTrongTuan = thuTrongTuan;
    }

    public int getIdThoiGianHoc() {
        return idThoiGianHoc;
    }

    public void setIdThoiGianHoc(int idThoiGianHoc) {
        this.idThoiGianHoc = idThoiGianHoc;
    }

    public String getTietHoc() {
        return tietHoc;
    }

    public void setTietHoc(String tietHoc) {
        this.tietHoc = tietHoc;
    }

    public String getThuTrongTuan() {
        return thuTrongTuan;
    }

    public void setThuTrongTuan(String thuTrongTuan) {
        this.thuTrongTuan = thuTrongTuan;
    }

    @Override
    public String toString() {
        return "thoiGianHoc{" +
                "tietHoc='" + tietHoc + '\'' +
                ", thuTrongTuan='" + thuTrongTuan + '\'' +
                '}';
    }
}
