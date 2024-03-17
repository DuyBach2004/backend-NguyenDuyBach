package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "userBinhLuan")
public class userBinhLuan {
    @Id
    @Column(name="id")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_binhluan")
    private binhluan binhluan;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private user user;

    public userBinhLuan(int id, vn.iback.studentmanager.entity.binhluan binhluan, vn.iback.studentmanager.entity.user user) {
        this.id = id;
        this.binhluan = binhluan;
        this.user = user;
    }

    public userBinhLuan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public vn.iback.studentmanager.entity.binhluan getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(vn.iback.studentmanager.entity.binhluan binhluan) {
        this.binhluan = binhluan;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }
}
