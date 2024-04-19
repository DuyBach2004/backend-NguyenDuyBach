package vn.iback.studentmanager.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import vn.iback.studentmanager.entity.binhluan;
import vn.iback.studentmanager.entity.user;

import java.sql.Timestamp;

public class phanhoiDTO {
    private int id;

    private String content;
    private int idBinhluan;
    private int like;
    private String user;
    private String thoigianphanhoi;

    public phanhoiDTO() {
    }

    public phanhoiDTO(int id, String content, int idBinhluan, int like, String user, String thoigianphanhoi) {
        this.id = id;
        this.content = content;
        this.idBinhluan = idBinhluan;
        this.like = like;
        this.user = user;
        this.thoigianphanhoi = thoigianphanhoi;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdBinhluan() {
        return idBinhluan;
    }

    public void setIdBinhluan(int idBinhluan) {
        this.idBinhluan = idBinhluan;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getThoigianphanhoi() {
        return thoigianphanhoi;
    }

    public void setThoigianphanhoi(String thoigianphanhoi) {
        this.thoigianphanhoi = thoigianphanhoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
