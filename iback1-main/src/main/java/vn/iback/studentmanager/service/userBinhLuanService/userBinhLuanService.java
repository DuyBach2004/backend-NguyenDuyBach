package vn.iback.studentmanager.service.userBinhLuanService;

import vn.iback.studentmanager.entity.userBinhLuan;
import vn.iback.studentmanager.entity.userPost;

import java.util.List;

public interface userBinhLuanService {
    public userBinhLuan findById(int id);
    public void save(userBinhLuan userBinhLuan);
    public void update(userBinhLuan userBinhLuan);
    public List<userBinhLuan> findAll();
    public void deleteById(int id);
}
