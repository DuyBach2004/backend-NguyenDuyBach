package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.nootebookService;
import vn.iback.studentmanager.service.teacherService;
import vn.iback.studentmanager.web.TeacherWeb;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private teacherService teacherService;
    private classService classService;
    private khoaService khoaService;
    private nootebookService nootebookService;
    private lopService lopService;
    @Autowired
    public TeacherController(vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.lopSevice.lopService lopService) {
        this.teacherService = teacherService;
        this.classService = classService;
        this.khoaService = khoaService;
        this.nootebookService = nootebookService;
        this.lopService = lopService;
    }

    @GetMapping("/create")
    public String create(Model model){
        TeacherWeb teacherWeb = new TeacherWeb();
        model.addAttribute("teacherList",teacherWeb);
        return "staff/create";
    }
    @GetMapping("/showHomeTeacher")
    public String showListTeacher(Model model){
//        model.addAttribute("classSchool", classSchool);
        List<staff> showListTeacher=teacherService.findAllTeacher();
        model.addAttribute("showListTeacher",showListTeacher);

        return "staff/teacher";
    }
    @PostMapping("/update-khoa")
    public String updateKhoa(@ModelAttribute("teacher") staff teacher,@RequestParam("id") String id,Model model){
        khoa khoa=khoaService.findkyHocid(id);
        teacher.setMaKhoa(khoa);
        teacherService.update(teacher);
        return "redirect:/teacher/showHomeTeacher";
    }
    @GetMapping("/showListTeacher-lop")
    public String showListTeacherLop(@RequestParam("id") String id,Model model){
        lop lop=lopService.findLopById(id);
        List<staff> teacherList=teacherService.findAllTeacher();
        model.addAttribute("showListTeacher",teacherList);
        model.addAttribute("lop",lop);
        return "staff/teacher";

    }
    @GetMapping("/showListTeacher-class")
    public String showListTeacherClass(@RequestParam("id") int id,Model model){
        classSchool classSchool=classService.findClassByid(id);
        List<staff> teacherList=teacherService.findAllTeacher();
        model.addAttribute("showListTeacher",teacherList);
        model.addAttribute("classSchool",classSchool);
        return "staff/teacher";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes){
        teacherService.deleteById(id);
//        model.addAttribute("classSchool",classSchool);
        return "redirect:/teacher/showHomeTeacher";
    }
    @GetMapping("/showListTeacher")
    public String showListTeacher(@RequestParam("id") String id  ,Model model){
        notebook notebook=nootebookService.findNotebookByid(Integer.parseInt(id));
        List<staff> showListTeacher=teacherService.findAllTeacher();
        model.addAttribute("showListTeacher",showListTeacher);
        model.addAttribute("notebook",notebook);
        return "staff/teacher";
    }
    @GetMapping("/shoPageUpdate")
    public String showPageUpdate(@RequestParam("id") String id, Model model){
        staff teacher=teacherService.findTeacherByid(id);
        model.addAttribute("teacher",teacher);
        return "staff/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("teacher") staff staff,Model model){

        teacherService.update(staff);
        return "redirect:/teacher/showHomeTeacher";
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("teacherList") TeacherWeb teacherWeb, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "staff/create";
        }
        staff teacher= new staff();
        String timeCurrently=String.valueOf(System.currentTimeMillis());
        Random random= new Random();
        int randomNumber=random.nextInt(100000);
        String teacherid= "GV"+randomNumber;
        teacher.setStaff_id(teacherid);
        teacher.setFullName(teacherWeb.getFullName());
        teacher.setBirthday(teacherWeb.getBirthday());
        teacher.setGender(teacherWeb.getGender());
        teacher.setEmail(teacherWeb.getEmail());
        teacher.setRole(teacherWeb.getRole());
        teacher.setDayIntoSchool(teacherWeb.getDayIntoSchool());
        teacher.setPhoneNumber(teacherWeb.getPhoneNumber());
        teacherService.save(teacher);

        return "redirect:/teacher/showHomeTeacher";

    }

}
