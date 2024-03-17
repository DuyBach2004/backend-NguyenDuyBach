package vn.iback.studentmanager.service.userPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.UserPostResponsitory;
import vn.iback.studentmanager.entity.userPost;

import java.util.List;
@Service
public class userPostServiceImpl implements userPostService {
    private UserPostResponsitory userPostResponsitory;
    @Autowired
    public userPostServiceImpl(UserPostResponsitory userPostResponsitory) {
        this.userPostResponsitory = userPostResponsitory;
    }

    @Override
    public userPost findById(int id) {
        return userPostResponsitory.getById(id);
    }

    @Override
    public void save(userPost userPost) {
        userPostResponsitory.save(userPost);
    }

    @Override
    public void update(userPost userPost) {
        userPostResponsitory.saveAndFlush(userPost);
    }

    @Override
    public List<userPost> findAll() {
        return userPostResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {
            userPostResponsitory.deleteById(id);
    }
}
