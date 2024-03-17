package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "userPhanHoi")
public class userPhanHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private user user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_phanhoi")
    private phanHoi phanhoi;

    public userPhanHoi(int id, vn.iback.studentmanager.entity.user user, phanHoi phanhoi) {
        this.id = id;
        this.user = user;
        this.phanhoi = phanhoi;
    }

    public userPhanHoi() {
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

    public phanHoi getPhanhoi() {
        return phanhoi;
    }

    public void setPhanhoi(phanHoi phanhoi) {
        this.phanhoi = phanhoi;
    }
}
