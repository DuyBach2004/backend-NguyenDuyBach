package vn.iback.studentmanager.service.lichHocService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.LichHocRespository;
import vn.iback.studentmanager.entity.lichHoc;

import java.util.List;
@Service
public class lichHocServiceImpl implements lichHocService {
    private LichHocRespository lichHocRespository;
    @Autowired
    public lichHocServiceImpl(LichHocRespository lichHocRespository) {
        this.lichHocRespository = lichHocRespository;
    }

    @Override
    public void save(lichHoc lichHoc) {
        lichHocRespository.save(lichHoc);
    }

    @Override
    public void update(lichHoc lichHoc) {
        lichHocRespository.saveAndFlush(lichHoc);
    }

    @Override
    public lichHoc findkyHocid(int maLichHoc) {
        return lichHocRespository.getById(maLichHoc);
    }

    @Override
    public List<lichHoc> findAllLichHoc() {
        return lichHocRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
            lichHocRespository.deleteById(id);
    }
}
