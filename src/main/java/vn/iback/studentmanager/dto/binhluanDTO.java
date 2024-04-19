package vn.iback.studentmanager.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.user;

import java.sql.Blob;
import java.sql.Timestamp;

public class binhluanDTO {
    private int id;
    private String content;
    private int like;
    private int maBaiViet;
    private String username;
    private String thoigiandangbai;


    public binhluanDTO(int id, String content, int like, int maBaiViet, String username, String thoigiandangbai) {
        this.id = id;
        this.content = content;
        this.like = like;
        this.maBaiViet = maBaiViet;
        this.username = username;
        this.thoigiandangbai = thoigiandangbai;
    }

    public binhluanDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getMaBaiViet() {
        return maBaiViet;
    }

    public void setMaBaiViet(int maBaiViet) {
        this.maBaiViet = maBaiViet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThoigiandangbai() {
        return thoigiandangbai;
    }

    public void setThoigiandangbai(String thoigiandangbai) {
        this.thoigiandangbai = thoigiandangbai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
