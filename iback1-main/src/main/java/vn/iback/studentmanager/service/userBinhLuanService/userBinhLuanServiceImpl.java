package vn.iback.studentmanager.service.userBinhLuanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.UserBinhLuanResponsitory;
import vn.iback.studentmanager.entity.userBinhLuan;

import java.util.List;
@Service
public class userBinhLuanServiceImpl implements userBinhLuanService {
    private UserBinhLuanResponsitory userBinhLuanResponsitory;
    @Autowired
    public userBinhLuanServiceImpl(UserBinhLuanResponsitory userBinhLuanResponsitory) {
        this.userBinhLuanResponsitory = userBinhLuanResponsitory;
    }

    @Override
    public userBinhLuan findById(int id) {
        return userBinhLuanResponsitory.getById(id);
    }

    @Override
    public void save(userBinhLuan userBinhLuan) {
        userBinhLuanResponsitory.save(userBinhLuan);
    }

    @Override
    public void update(userBinhLuan userBinhLuan) {
        userBinhLuanResponsitory.saveAndFlush(userBinhLuan);
    }

    @Override
    public List<userBinhLuan> findAll() {
        return userBinhLuanResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {
            userBinhLuanResponsitory.deleteById(id);
    }
}
