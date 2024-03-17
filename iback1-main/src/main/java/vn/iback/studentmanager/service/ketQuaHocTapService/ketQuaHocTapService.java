package vn.iback.studentmanager.service.ketQuaHocTapService;

import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.ketQuaHocTap;

import java.util.List;

public interface ketQuaHocTapService {
    public void save(ketQuaHocTap ketQuaHocTap);
    public void update(ketQuaHocTap ketQuaHocTap);
    public ketQuaHocTap findKetQuaHocTapById(int maDiem);
    public List<ketQuaHocTap> findAllKetQuaHocTap();
    public void deleteById(int id);
}
