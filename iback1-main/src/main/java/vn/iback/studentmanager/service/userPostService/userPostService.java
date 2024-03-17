package vn.iback.studentmanager.service.userPostService;

import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.entity.userPost;

import java.util.List;

public interface userPostService {
    public userPost findById(int id);
    public void save(userPost userPost);
    public void update(userPost userPost);
    public List<userPost> findAll();
    public void deleteById(int id);

}
