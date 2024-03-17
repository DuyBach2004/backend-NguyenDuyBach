package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.hokyservice.hokyService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lichHocService.lichHocService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private classService classService;
    private nootebookService nootebookService;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private Userservice userservice;
    private thoiGianHocService thoiGianHocService;
    private hokyService hokyService;
    private khoaService khoaService;
    private lopService lopService;
    private diemService diemService;
    private ketQuaHocTapService ketQuaHocTapService;
    private teacherService teacherService;
    private subjectService subjectService;
    private specializationService specializationService;
    private lichHocService lichHocService;
    @Autowired
    public HomeController(vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.nootebookService nootebookService, StudentIntoNoteBookService studentIntoNoteBookService, Userservice userservice, vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService, vn.iback.studentmanager.service.hokyservice.hokyService hokyService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.lopSevice.lopService lopService, vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.subjectService subjectService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService) {
        this.classService = classService;
        this.nootebookService = nootebookService;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.userservice = userservice;
        this.thoiGianHocService = thoiGianHocService;
        this.hokyService = hokyService;
        this.khoaService = khoaService;
        this.lopService = lopService;
        this.diemService = diemService;
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.specializationService = specializationService;
        this.lichHocService = lichHocService;
    }

    @GetMapping()
    public String showHomePage(@RequestParam("username") String username , Model model){
        List<notebook> notebookList=nootebookService.findAllNote();
        List<classSchool> classSchoolList=classService.findAllClass();
        user user=userservice.findByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("notebookList",notebookList);
        model.addAttribute("classSchoolList",classSchoolList);
        return "home";
    }
    @GetMapping("/showHome")
    public String showHome(Model model){
        List<notebook> notebookList=nootebookService.findAllNote();
        List<classSchool> classSchoolList=classService.findAllClass();
        List<thoiGianHoc> thoiGianHocs=thoiGianHocService.findAllThoiGianHoc();
        List<kyHoc> kyHocs=hokyService.findAllKyHoc();
        List<khoa> khoaList=khoaService.findAllKhoa();
        List<lop> lopList=lopService.findAllLop();
        List<diem> diems=diemService.findAllDiem();
        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
        List<staff> showListTeacher=teacherService.findAllTeacher();
        List<subjects> subjects=subjectService.findAll();
        List<specialization> specializations=specializationService.findAllSpecialization();
        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
        List<user> userList=userservice.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("lichHocList",lichHocList);
        model.addAttribute("specializations",specializations);
        model.addAttribute("subjectList",subjects);
        model.addAttribute("showListTeacher",showListTeacher);
        model.addAttribute("ketQuaHocTapList",ketQuaHocTapList);
        model.addAttribute("diems",diems);
        model.addAttribute("lopList",lopList);
        model.addAttribute("khoaList",khoaList);
        model.addAttribute("hocKyList",kyHocs);
        model.addAttribute("thoiGianHocList",thoiGianHocs);
        model.addAttribute("notebookList",notebookList);
        model.addAttribute("classSchoolList",classSchoolList);
        return "home";
    }



}
