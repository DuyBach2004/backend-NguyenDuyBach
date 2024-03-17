package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "image_baiviet")
public class image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_image")
    private int id;
    @Column(name = "path_image")
    private String path;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_baiviet")
    private baiViet baiViet;

    public image(int id, String path, vn.iback.studentmanager.entity.baiViet baiViet) {
        this.id = id;
        this.path = path;
        this.baiViet = baiViet;
    }

    public image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public vn.iback.studentmanager.entity.baiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(vn.iback.studentmanager.entity.baiViet baiViet) {
        this.baiViet = baiViet;
    }
}
