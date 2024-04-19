package vn.iback.studentmanager.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import vn.iback.studentmanager.entity.user;

import java.sql.Timestamp;

public class postDTO {
    private int id;
    private String title;
    private String image;
    private int like;
    private String username;
    private String thoigianbinhluan;

    public postDTO(int id, String title, String image, int like, String username, String thoigianbinhluan) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.like = like;
        this.username = username;
        this.thoigianbinhluan = thoigianbinhluan;
    }

    public postDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThoigianbinhluan() {
        return thoigianbinhluan;
    }

    public void setThoigianbinhluan(String thoigianbinhluan) {
        this.thoigianbinhluan = thoigianbinhluan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
