package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "userPost")
public class userPost {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private user user;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_baiviet")
    private baiViet baiViet;
    @Column(name="trangthailike")
    private boolean trangThaiLike;

    public userPost(int id, vn.iback.studentmanager.entity.user user, vn.iback.studentmanager.entity.baiViet baiViet, boolean trangThaiLike) {
        this.id = id;
        this.user = user;
        this.baiViet = baiViet;
        this.trangThaiLike = trangThaiLike;
    }

    public userPost() {
    }

    public int getId() {
        return id;
    }

    public boolean isTrangThaiLike() {
        return trangThaiLike;
    }

    public void setTrangThaiLike(boolean trangThaiLike) {
        this.trangThaiLike = trangThaiLike;
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

    public vn.iback.studentmanager.entity.baiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(vn.iback.studentmanager.entity.baiViet baiViet) {
        this.baiViet = baiViet;
    }
}
