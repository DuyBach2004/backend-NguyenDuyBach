package vn.iback.studentmanager.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Blob;
import java.sql.Timestamp;

public class post {
    private String content;
    private String image;
    private String username;
    private Timestamp thoigianbinhluan;
    @JsonCreator
    public post(@JsonProperty("content_js") String content,@JsonProperty("image") String image,@JsonProperty("username") String username,@JsonProperty("thoigianbinhluan") Timestamp thoigianbinhluan) {
        this.content = content;
        this.image = image;
        this.username = username;
        this.thoigianbinhluan = thoigianbinhluan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getThoigianbinhluan() {
        return thoigianbinhluan;
    }

    public void setThoigianbinhluan(Timestamp thoigianbinhluan) {
        this.thoigianbinhluan = thoigianbinhluan;
    }
}
