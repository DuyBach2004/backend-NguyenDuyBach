package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "phan_hoi")
public class phanHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_phanhoi")
    private int idPhanhoi;
    @Column(name="content")
    private String content;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_binhluan")
    private binhluan binhluan;
    @Column(name = "like1")
    private int like;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private user user;
    @Column(name = "thoigianphanhoi")
    private Timestamp thoigianphanhoi;

    public phanHoi(int idPhanhoi, String content, vn.iback.studentmanager.entity.binhluan binhluan, int like, vn.iback.studentmanager.entity.user user, Timestamp thoigianphanhoi) {
        this.idPhanhoi = idPhanhoi;
        this.content = content;
        this.binhluan = binhluan;
        this.like = like;
        this.user = user;
        this.thoigianphanhoi = thoigianphanhoi;
    }

    public phanHoi() {
    }

    public int getIdPhanhoi() {
        return idPhanhoi;
    }

    public void setIdPhanhoi(int idPhanhoi) {
        this.idPhanhoi = idPhanhoi;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public vn.iback.studentmanager.entity.binhluan getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(vn.iback.studentmanager.entity.binhluan binhluan) {
        this.binhluan = binhluan;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }

    public Timestamp getThoigianphanhoi() {
        return thoigianphanhoi;
    }

    public void setThoigianphanhoi(Timestamp thoigianphanhoi) {
        this.thoigianphanhoi = thoigianphanhoi;
    }
}
