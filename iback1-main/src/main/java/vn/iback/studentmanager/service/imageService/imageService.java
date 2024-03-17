package vn.iback.studentmanager.service.imageService;

import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.image;

import java.util.List;

public interface imageService {
    public void save(image image);
    public void update(image image);
    public void saveAndUpdate(image image);
    public image findImageByid(int id);
    public List<image> findAllImage();
    public void deleteById(int id);

}
