package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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

@RestController
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
    @GetMapping("")
    public List<staff> showPageClassApi(Model model){
        List<staff> showListTeacher = teacherService.findAllTeacher();
        return showListTeacher;
    }

    @GetMapping("/ma-giao-vien")
    public staff getById(@RequestParam String id){
        try {
            staff staff1 = teacherService.findTeacherByid(id);
            if (staff1 != null){
                return staff1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy giáo viên với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-giao-vien")
    public ResponseEntity deleteTeacherApi(@RequestParam String id){
        staff existing = teacherService.findTeacherByid(id);
        if (existing != null){
            teacherService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-giao-vien")
    public ResponseEntity updateTeacher(@RequestParam String id, @RequestBody staff staff, Model model){
        staff staff1 = teacherService.findTeacherByid(id);
        try {
            if (staff1 != null){
                staff1.setMaKhoa(staff.getMaKhoa());
                staff1.setRole(staff.getRole());
                staff1.setGender(staff.getGender());
                staff1.setBirthday(staff.getBirthday());
                staff1.setFullName(staff.getFullName());
                staff1.setDayIntoSchool(staff.getDayIntoSchool());
                staff1.setPhoneNumber(staff.getPhoneNumber());
                staff1.setEmail(staff.getStaff_id());
                teacherService.update(staff1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createTeacherApi(@RequestBody staff teacher){
        try {
            if (teacher != null){
        String timeCurrently=String.valueOf(System.currentTimeMillis());
        Random random= new Random();
        int randomNumber=random.nextInt(100000);
        String teacherid= "GV"+randomNumber;
        teacher.setStaff_id(teacherid);
                teacherService.save(teacher);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/create")
//    public String create(Model model){
//        TeacherWeb teacherWeb = new TeacherWeb();
//        model.addAttribute("teacherList",teacherWeb);
//        return "staff/create";
//    }
//    @PostMapping("/create-rest-api")
//    public staff createApi(@RequestBody staff teacher){
//        String timeCurrently=String.valueOf(System.currentTimeMillis());
//        Random random= new Random();
//        int randomNumber=random.nextInt(100000);
//        String teacherid= "GV"+randomNumber;
//        teacher.setStaff_id(teacherid);
//
//        return teacher;
//    }
//
//    @GetMapping("/showHomeTeacher-rest-api")
//    public List<staff> showListTeacherApi(){
//        List<staff> showListTeacher=teacherService.findAllTeacher();
//        return showListTeacher;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteLichApi(@PathVariable String id, Model model){
//        staff existing=teacherService.findTeacherByid(id);
//        if (existing!=null){
//            teacherService.deleteById(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public staff updateLop(@PathVariable String id,@RequestBody staff staff,Model model){
//        vn.iback.studentmanager.entity.staff staff1=teacherService.findTeacherByid(id);
//        staff1.setMaKhoa(staff.getMaKhoa());
//        staff1.setRole(staff.getRole());
//        staff1.setGender(staff.getGender());
//        staff1.setBirthday(staff.getBirthday());
//        staff1.setFullName(staff.getFullName());
//        staff1.setDayIntoSchool(staff.getDayIntoSchool());
//        staff1.setPhoneNumber(staff.getPhoneNumber());
//        staff1.setEmail(staff.getStaff_id());
//        teacherService.update(staff1);
//        return staff1;
//    }


//    @PostMapping("/update-khoa")
//    public String updateKhoa(@RequestParam("selectedOption") String id,@RequestParam("idTeacher") String idTeacher,Model model){
//
//        staff teacher=teacherService.findTeacherByid(idTeacher);
//        khoa khoa=khoaService.findkyHocid(id);
//        teacher.setMaKhoa(khoa);
//        teacherService.update(teacher);
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-khoa")
//    public String updateKhoa(@ModelAttribute("teacher") staff teacher,@RequestParam("id") String id,Model model){
//        khoa khoa=khoaService.findkyHocid(id);
//        teacher.setMaKhoa(khoa);
//        teacherService.update(teacher);
//        return "redirect:/teacher/showHomeTeacher";
//    }
//    @GetMapping("/showListTeacher-lop")
//    public String showListTeacherLop(@RequestParam("id") String id,Model model){
//        lop lop=lopService.findLopById(id);
//        List<staff> teacherList=teacherService.findAllTeacher();
//        model.addAttribute("showListTeacher",teacherList);
//        model.addAttribute("lop",lop);
//        return "staff/teacher";
//
//    }
//    @GetMapping("/showListTeacher-class")
//    public String showListTeacherClass(@RequestParam("id") int id,Model model){
//        classSchool classSchool=classService.findClassByid(id);
//        List<staff> teacherList=teacherService.findAllTeacher();
//        model.addAttribute("showListTeacher",teacherList);
//        model.addAttribute("classSchool",classSchool);
//        return "staff/teacher";
//
//    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes){
//        teacherService.deleteById(id);
////        model.addAttribute("classSchool",classSchool);
////        return "redirect:/teacher/showHomeTeacher";
//        return "redirect:/home/showHome";
//    }
//    @GetMapping("/showListTeacher")
//    public String showListTeacher(@RequestParam("id") String id  ,Model model){
//        notebook notebook=nootebookService.findNotebookByid(Integer.parseInt(id));
//        List<staff> showListTeacher=teacherService.findAllTeacher();
//        model.addAttribute("showListTeacher",showListTeacher);
//        model.addAttribute("notebook",notebook);
//        return "staff/teacher";
//    }
//    @GetMapping("/shoPageUpdate")
//    public String showPageUpdate(@RequestParam("id") String id, Model model){
//        staff teacher=teacherService.findTeacherByid(id);
//        model.addAttribute("teacher",teacher);
//        return "staff/update";
//    }

//    @PostMapping("/update")
//    public String update(@ModelAttribute("teacher") staff staff,Model model){
//
//        teacherService.update(staff);
////        return "redirect:/teacher/showHomeTeacher";
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/process")
//    public String process(@Valid @ModelAttribute("teacherList") TeacherWeb teacherWeb, BindingResult result, Model model, HttpSession session){
//        if(result.hasErrors()){
//            return "staff/create";
//        }
//        staff teacher= new staff();
//        String timeCurrently=String.valueOf(System.currentTimeMillis());
//        Random random= new Random();
//        int randomNumber=random.nextInt(100000);
//        String teacherid= "GV"+randomNumber;
//        teacher.setStaff_id(teacherid);
//        teacher.setFullName(teacherWeb.getFullName());
//        teacher.setBirthday(teacherWeb.getBirthday());
//        teacher.setGender(teacherWeb.getGender());
//        teacher.setEmail(teacherWeb.getEmail());
//        teacher.setRole(teacherWeb.getRole());
//        teacher.setDayIntoSchool(teacherWeb.getDayIntoSchool());
//        teacher.setPhoneNumber(teacherWeb.getPhoneNumber());
//        teacherService.save(teacher);
//
////        return "redirect:/teacher/showHomeTeacher";
//        return "redirect:/home/showHome";
//
//    }

}
