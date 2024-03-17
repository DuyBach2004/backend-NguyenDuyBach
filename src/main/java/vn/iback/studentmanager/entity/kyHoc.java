package vn.iback.studentmanager.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "ky_hoc")
public class kyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nam_hoc")
    private String namHoc;
    @Column(name = "hoc_ky")
    private String hocKi;

    public kyHoc() {
    }

    public kyHoc(int id, String namHoc, String hocKi) {
        this.id = id;
        this.namHoc = namHoc;
        this.hocKi = hocKi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }
}
