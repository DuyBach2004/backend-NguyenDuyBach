package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.hokyservice.hokyService;
import vn.iback.studentmanager.service.lichHocService.lichHocService;
import vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService;
import vn.iback.studentmanager.web.ClassWeb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/class")
public class ClassController {
    private teacherService teacherService;
    private classService classService;
    private nootebookService nootebookService;
    private hokyService hokyService;
    private subjectService subjectService;
    private Userservice userservice;
    private diemService diemService;
    private thoiGianHocService thoiGianHocService;
    private lichHocService lichHocService;
    @Autowired
    public ClassController(vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.hokyservice.hokyService hokyService, vn.iback.studentmanager.service.subjectService subjectService, Userservice userservice, vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.thoiGianHocService.thoiGianHocService thoiGianHocService, vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService) {
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
    @GetMapping("")
    public List<classSchool> showPageClassApi(Model model){
        List<classSchool> classSchools = classService.findAllClass();
        return classSchools;
    }

    @GetMapping("/ma-lop-hoc")
    public classSchool getById(@RequestParam int id){
        try {
            classSchool classSchool1 = classService.findClassByid(id);
            if (classSchool1 != null){
                return classSchool1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy lớp học với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-lop-hoc")
    public ResponseEntity deleteClassApi(@RequestParam int id){
        classSchool existing = classService.findClassByid(id);
        if (existing != null){
            classService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-lop-hoc")
    public ResponseEntity updateClass(@RequestParam int id, @RequestBody classSchool classSchool, Model model){
        classSchool classSchool1 = classService.findClassByid(id);
        try {
            if (classSchool1 != null){
                classSchool1.setSoTiet(classSchool.getSoTiet());
                classSchool1.setThoiGianHocs(classSchool.getThoiGianHocs());
                classService.update(classSchool1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createApi(@RequestBody classSchool classSchool){
        try {
            if (classSchool != null){
                classService.save(classSchool);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/showListClassApi")
//    public List<classSchool> showListClassApi(){
//        List<classSchool> classSchoolList=classService.findAllClass();
//        return classSchoolList;
//    }
//
//    @PostMapping("/create-rest-api")
//    public ClassWeb createApi(Model model){
//        ClassWeb classSchool=new ClassWeb();
//        return classSchool;
//    }
//
//    @DeleteMapping("/delete-rest-api/{id}")
//    public ResponseEntity<Void> delete(@PathVariable int id, Model model){
//        classSchool existing= classService.findClassByid(id);
//        classService.deleteById(id);
//        if (existing!=null){
//            classService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/update-rest-api/{id}")
//    public classSchool updateApi(@PathVariable String id,@RequestBody classSchool classSchool,Model model){
//
//        classService.update(classSchool);
//        return classSchool;
//    }



//    @PutMapping("/updateTeacher-rest-api/{TeacherId}")
//    public ResponseEntity updateTeacher(@RequestBody classSchool classSchool,@PathVariable String id,Model model){
//        try {
//            staff teacher= teacherService.findTeacherByid(id);
//            classSchool.setHomeroomTeacher(teacher);
//            classService.update(classSchool);
//            // Trả về thông báo thành công
//            return ResponseEntity.ok("success");
//        } catch (Exception e) {
//            // Xử lý lỗi (nếu có)
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi khi đăng xuất: " + e.getMessage());
//        }
//    }
//    @PostMapping("/updateTeacher")
//    public String updateTeacher(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("TeacherId") String id,Model model){
//        staff teacher= teacherService.findTeacherByid(id);
//        classSchool.setHomeroomTeacher(teacher);
//        classService.update(classSchool);
//        return "redirect:/class/showHomeClass";
//    }
//@GetMapping("/studentEnroll")
//public String studentErroll(@RequestParam("id") int id, @RequestParam("username") String username, Model model, RedirectAttributes redirectAttributes) {
//        user user=userservice.findByUsername(username);
//        classSchool classSchool=classService.findClassByid(id);
//        notebook notebook=new notebook();
//    Collection<user> users=new ArrayList<>();
//    users.add(user);
//        notebook.setStaff(user);
//        notebook.setClassSchool(classSchool);
//        nootebookService.update(notebook);
//        diem diem=new diem();
//        diem.setNotebook(notebook);
//        diemService.save(diem);
//    redirectAttributes.addAttribute("username", username);
//        return "redirect:/homeUser/showHome";
//}
//    @PostMapping("/update-hocKy")
//    public String updateHocKy(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") int id,Model model){
//        kyHoc kyHoc=hokyService.findkyHocid(id);
//        classSchool.setKyHoc(kyHoc);
//
//        System.out.println(classSchool.getIdClass());
//        classService.update(classSchool);
//        return "redirect:/class/showHomeClass";
//    }
//    @PostMapping("/update-hocKy2")
//    public String updateHocKy2(@RequestParam("selectedOption") String selectedOption,@RequestParam("idclass") int idClass,Model model){
//        int id= Integer.parseInt(selectedOption);
//        kyHoc kyHoc=hokyService.findkyHocid(id);
//        classSchool classSchool=classService.findClassByid(idClass);
//        classSchool.setKyHoc(kyHoc);
//
////        System.out.println(selectedOption);
//        classService.update(classSchool);
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-thoiGianHoc")
//    public String updateThoiGianHoc(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") int id,Model model){
//
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        boolean check=false;
//        for (lichHoc lichHoc1:lichHocList) {
//            if (lichHoc1.getThoiGianHoc().getIdThoiGianHoc()==id&&lichHoc1.getClassSchool().getIdClass()==classSchool.getIdClass()){
//                        check=false;
//                System.out.println("hello1");
//                        break;
//                //                return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//            }else {
//                System.out.println("hello2");
//                check=true;
//            }
//        }
//            if(check==false){
//                return "redirect:/home/showHome";
//            }else {
//
//                thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(id);
//                lichHoc lichHoc=new lichHoc();
//                lichHoc.setThoiGianHoc(thoiGianHoc);
//                lichHoc.setClassSchool(classSchool);
//                lichHocService.save(lichHoc);
//                return "redirect:/home/showHome";
//            }
//
//    }
//    @PostMapping("/update-thoiGianHoc2")
//    public String updateThoiGianHoc2(@RequestParam("idclass") int idClass,@RequestParam("id") int id,Model model){
//        classSchool classSchool=classService.findClassByid(idClass);
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        boolean check=false;
//        for (lichHoc lichHoc1:lichHocList) {
//            if (lichHoc1.getThoiGianHoc().getIdThoiGianHoc()==id&&lichHoc1.getClassSchool().getIdClass()==classSchool.getIdClass()){
//                check=false;
//                System.out.println("hello1");
//                break;
//                //                return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//            }else {
//                System.out.println("hello2");
//                check=true;
//            }
//        }
//        if(check==false){
//            return "redirect:/home/showHome";
//        }else {
//
//            thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(id);
//            lichHoc lichHoc=new lichHoc();
//            lichHoc.setThoiGianHoc(thoiGianHoc);
//            lichHoc.setClassSchool(classSchool);
//            lichHocService.save(lichHoc);
//            return "redirect:/home/showHome";
//        }
//
//    }
//    @PostMapping("/update-thoiGianHoc3")
//    public String updateThoiGianHoc3(@RequestParam("selectedOption") String selectedOption,@RequestParam("idclass") int idClass ,Model model){
//        int id= Integer.parseInt(selectedOption);
//        classSchool classSchool=classService.findClassByid(idClass);
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        boolean check=false;
//        for (lichHoc lichHoc1:lichHocList) {
//            if (lichHoc1.getThoiGianHoc().getIdThoiGianHoc()==id&&lichHoc1.getClassSchool().getIdClass()==classSchool.getIdClass()){
//                check=false;
//                System.out.println("hello1");
//                break;
//                //                return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
//            }else {
//                System.out.println("hello2");
//                check=true;
//            }
//        }
//        if(check==false){
//            System.out.println("hello3");
//            thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(id);
//            lichHoc lichHoc=new lichHoc();
//            lichHoc.setThoiGianHoc(thoiGianHoc);
//            lichHoc.setClassSchool(classSchool);
//            lichHocService.save(lichHoc);
//            return "redirect:/home/showHome";
//        }else {
//            System.out.println("hello3");
//            thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(id);
//            lichHoc lichHoc=new lichHoc();
//            lichHoc.setThoiGianHoc(thoiGianHoc);
//            lichHoc.setClassSchool(classSchool);
//            lichHocService.save(lichHoc);
//
//        }
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-teacher")
//    public String updateTeacher(@RequestParam("selectedOption") String selectedOption,@RequestParam("idclass") int id,Model model){
//        String idteacher= selectedOption;
//        staff teacher=teacherService.findTeacherByid(idteacher);
//        classSchool classSchool=classService.findClassByid(id);
//        classSchool.setHomeroomTeacher(teacher);
//        classService.update(classSchool);
////        return "redirect:/class/showHomeClass";
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-subject")
//    public String updateSubject(@RequestParam("selectedOption") String selectedOption,@RequestParam("idclass") int id,Model model){
//        String idSubject= selectedOption;
//        classSchool classSchool=classService.findClassByid(id);
//        subjects subjects=subjectService.findNSubjectByid(idSubject);
//        classSchool.setSubjects(subjects);
//        classService.update(classSchool);
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/process")
//    public String process(@Valid @ModelAttribute("classSchool")ClassWeb classWeb, BindingResult result, Model model, HttpSession session){
//        String className=classWeb.getClassName();
//        //form validation
//        if(result.hasErrors()){
//            return "class/create";
//        }
//        //kieu tra classname ton tai
//        classSchool classExisting=classService.findClassByName(className);
//        if(classExisting!=null){
//            model.addAttribute("ClassWeb",new classSchool());
//            model.addAttribute("my_error","Lớp học đã tồn tại");
//            return "class/create";
//        }
//        //neu chua ton tai
//
//        classSchool classSchool=new classSchool();
//        // Lấy thời gian hiện tại (ví dụ, làm chuỗi nó làm được)
//        String timeCurrently = String.valueOf(System.currentTimeMillis());
//
//// Tạo một số ngẫu nhiên
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String classid = "DH" + timeCurrently + randomNumber;
//        classSchool.setEndDay(classWeb.getEndYear());
//        classSchool.setSoTiet(classWeb.getSoTiet());
//        classSchool.setSchoolYear(classWeb.getSchoolYear());
//        classSchool.setPhongHoc(classWeb.getClassName());
//        classService.save(classSchool);
//
//
////        return "redirect:/class/showHomeClass";
//        return "redirect:/home/showHome";
//
//    }


}
