package vn.iback.studentmanager.Controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.notebook;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.hokyservice.hokyService;
import vn.iback.studentmanager.service.lichHocService.lichHocService;
import vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/class-user")
public class ClassUserController {
    private vn.iback.studentmanager.service.teacherService teacherService;
    private vn.iback.studentmanager.service.classService classService;
    private vn.iback.studentmanager.service.nootebookService nootebookService;
    private vn.iback.studentmanager.service.hokyservice.hokyService hokyService;
    private vn.iback.studentmanager.service.subjectService subjectService;
    private Userservice userservice;
    private vn.iback.studentmanager.service.diemService.diemService diemService;
    private vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService;
    private vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService;
    @Autowired
    public ClassUserController(vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.hokyservice.hokyService hokyService, vn.iback.studentmanager.service.subjectService subjectService, Userservice userservice, vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService, vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService) {
        this.teacherService = teacherService;
        this.classService = classService;
        this.nootebookService = nootebookService;
        this.hokyService = hokyService;
        this.subjectService = subjectService;
        this.userservice = userservice;
        this.diemService = diemService;
        this.thoiGianHocService = thoiGianHocService;
        this.lichHocService = lichHocService;
    }
    @GetMapping("/studentEnroll")
    public String studentErroll(@RequestParam("id") int id, @RequestParam("username") String username, Model model, RedirectAttributes redirectAttributes) {
        user user=userservice.findByUsername(username);
        classSchool classSchool=classService.findClassByid(id);
        notebook notebook=new notebook();
        Collection<user> users=new ArrayList<>();
        users.add(user);
        notebook.setStaff(user);
        notebook.setClassSchool(classSchool);
        nootebookService.update(notebook);
        diem diem=new diem();
        diem.setNotebook(notebook);
        diemService.save(diem);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/homeUser/showHome";
    }

}
