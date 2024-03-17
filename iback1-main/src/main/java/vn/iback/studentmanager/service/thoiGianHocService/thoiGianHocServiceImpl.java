package vn.iback.studentmanager.service.thoiGianHocService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.ThoiGianHocRespository;
import vn.iback.studentmanager.entity.thoiGianHoc;

import java.util.List;
@Service
public class thoiGianHocServiceImpl implements thoiGianHocService {
    private ThoiGianHocRespository thoiGianHocRespository;
    @Autowired
    public thoiGianHocServiceImpl(ThoiGianHocRespository thoiGianHocRespository) {
        this.thoiGianHocRespository = thoiGianHocRespository;
    }

    @Override
    public void save(thoiGianHoc thoiGianHoc) {
        thoiGianHocRespository.save(thoiGianHoc);
    }

    @Override
    public void update(thoiGianHoc thoiGianHoc) {
        thoiGianHocRespository.saveAndFlush(thoiGianHoc);
    }

    @Override
    public thoiGianHoc findkyHocid(int thoiGianHoc) {
        return thoiGianHocRespository.getById(thoiGianHoc);
    }

    @Override
    public List<thoiGianHoc> findAllThoiGianHoc() {
        return thoiGianHocRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
            thoiGianHocRespository.deleteById(id);
    }
}
