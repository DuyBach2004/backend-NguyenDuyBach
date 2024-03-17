package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "binh_luan")
public class binhluan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_binhluan")
    private int id;
    @Column(name = "date")
    private Date day;
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    private Blob image;
    @Column(name = "like1")
    private int like;
    @Column(name = "tim")
    private int tim;
    @Column(name = "haha")
    private int haha;
    @Column(name = "dislike")
    private int dislike;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_baiviet")
    private baiViet baiViet;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private user user;
    @Column(name = "thoigiandangbai")
    private Timestamp thoigiandangbai;


    public binhluan(int id, Date day, String content, Blob image, int like, int tim, int haha, int dislike, vn.iback.studentmanager.entity.baiViet baiViet, vn.iback.studentmanager.entity.user user, Timestamp thoigiandangbai) {
        this.id = id;
        this.day = day;
        this.content = content;
        this.image = image;
        this.like = like;
        this.tim = tim;
        this.haha = haha;
        this.dislike = dislike;
        this.baiViet = baiViet;
        this.user = user;
        this.thoigiandangbai = thoigiandangbai;
    }

    public binhluan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTim() {
        return tim;
    }

    public void setTim(int tim) {
        this.tim = tim;
    }

    public int getHaha() {
        return haha;
    }

    public void setHaha(int haha) {
        this.haha = haha;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }

    public vn.iback.studentmanager.entity.baiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(vn.iback.studentmanager.entity.baiViet baiViet) {
        this.baiViet = baiViet;
    }

    public Timestamp getThoigiandangbai() {
        return thoigiandangbai;
    }

    public void setThoigiandangbai(Timestamp thoigiandangbai) {
        this.thoigiandangbai = thoigiandangbai;
    }
}
