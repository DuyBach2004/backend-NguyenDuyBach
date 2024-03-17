package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lichHoc")
public class lichHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLichHoc")
    private int idLichHoc;
    @ManyToOne
    @JoinColumn(name = "idthoiGianHoc")
    private thoiGianHoc thoiGianHoc;
    @ManyToOne
    @JoinColumn(name = "id_class")
    private classSchool classSchool;

    public lichHoc() {
    }

    public lichHoc(int idLichHoc, vn.iback.studentmanager.entity.thoiGianHoc thoiGianHoc, vn.iback.studentmanager.entity.classSchool classSchool) {
        this.idLichHoc = idLichHoc;
        this.thoiGianHoc = thoiGianHoc;
        this.classSchool = classSchool;
    }

    public int getIdLichHoc() {
        return idLichHoc;
    }

    public void setIdLichHoc(int idLichHoc) {
        this.idLichHoc = idLichHoc;
    }

    public vn.iback.studentmanager.entity.thoiGianHoc getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(vn.iback.studentmanager.entity.thoiGianHoc thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public vn.iback.studentmanager.entity.classSchool getClassSchool() {
        return classSchool;
    }

    public void setClassSchool(vn.iback.studentmanager.entity.classSchool classSchool) {
        this.classSchool = classSchool;
    }
}
