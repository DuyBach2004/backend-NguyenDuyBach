package vn.iback.studentmanager.service.binhluanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.BinhluanResponsitory;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.binhluan;

import java.util.ArrayList;
import java.util.List;
@Service
public class binhluanServiceImpl implements binhluanService {
    private BinhluanResponsitory binhluanResponsitory;
    @Autowired
    public binhluanServiceImpl(BinhluanResponsitory binhluanResponsitory) {
        this.binhluanResponsitory = binhluanResponsitory;
    }

    @Override
    public void save(binhluan binhluan) {
        binhluanResponsitory.save(binhluan);
    }

    @Override
    public void update(binhluan binhluan) {
        binhluanResponsitory.saveAndFlush(binhluan);
    }

    @Override
    public binhluan findClassByid(int id) {
        return binhluanResponsitory.getById(id);
    }

    @Override
    public List<binhluan> findAllBinhluan() {
        return binhluanResponsitory.findAll();
    }

    @Override
    public List<binhluan> findAllBinhluanOfBaiViet(baiViet baiViet) {
        List<binhluan> list1= binhluanResponsitory.findAll();
        List<binhluan> list2=new ArrayList<>();
        for (binhluan binhluan:list1) {
            if (binhluan.getBaiViet().getId()==baiViet.getId()){
                list2.add(binhluan);
            }
        }
        return list2;
    }

    @Override
    public void deleteById(int id) {

    }
}
