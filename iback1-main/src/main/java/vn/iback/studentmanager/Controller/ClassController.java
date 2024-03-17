package vn.iback.studentmanager.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

@Controller
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

    @GetMapping("/showListClass")
    public String showHomePage(@RequestParam("id") String id ,Model model){
        notebook notebook=nootebookService.findNotebookByid(Integer.parseInt(id));
        List<classSchool> classSchoolList=classService.findAllClass();
        model.addAttribute("classSchoolList",classSchoolList);
        model.addAttribute("notebook",notebook);
        return "class/home";
    }
    @GetMapping("/showHomeClass")
    public String showHomePageClass(Model model){
        List<classSchool> classSchoolList=classService.findAllClass();
        model.addAttribute("classSchoolList",classSchoolList);
        return "class/home";
    }
    @GetMapping("/create")
    public String create(Model model){
        ClassWeb classSchool=new ClassWeb();
        model.addAttribute("classSchool",classSchool);
        return "class/create";
    }
    @GetMapping("/shoPageUpdate")
    public String showPageUpdate(@RequestParam("id") int id, Model model){
        classSchool classSchool=classService.findClassByid(id);
        model.addAttribute("classSchool",classSchool);
        return "class/update";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id,Model model){
        classService.deleteById(id);
        return "redirect:/class/showHomeClass";
    }
    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("id") int id,Model model ){
        classSchool classSchool=classService.findClassByid(id);
        classService.deletehomeroomTeacher(classSchool);
        return "redirect:/home";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("classSchool") classSchool classSchool,Model model){

        classService.update(classSchool);
        return "redirect:/class/showHomeClass";
    }
//    @PostMapping("/updateTeacher")
//    public String updateTeacher(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("TeacherId") String id,Model model){
//        staff teacher= teacherService.findTeacherByid(id);
//        classSchool.setHomeroomTeacher(teacher);
//        classService.update(classSchool);
//        return "redirect:/class/showHomeClass";
//    }
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
    @PostMapping("/update-hocKy")
    public String updateHocKy(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") int id,Model model){
        kyHoc kyHoc=hokyService.findkyHocid(id);
        classSchool.setKyHoc(kyHoc);

        System.out.println(classSchool.getIdClass());
        classService.update(classSchool);
        return "redirect:/class/showHomeClass";
    }
    @PostMapping("/update-thoiGianHoc")
    public String updateThoiGianHoc(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") int id,Model model){

        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
        boolean check=false;
        for (lichHoc lichHoc1:lichHocList) {
            if (lichHoc1.getThoiGianHoc().getIdThoiGianHoc()==id&&lichHoc1.getClassSchool().getIdClass()==classSchool.getIdClass()){
                        check=false;
                System.out.println("hello1");
                        break;
                //                return "redirect:/thoi-gian-hoc/showPageThoiGianHoc";
            }else {
                System.out.println("hello2");
                check=true;
            }
        }
            if(check==false){
                return "redirect:/class/showHomeClass";
            }else {

                thoiGianHoc thoiGianHoc=thoiGianHocService.findkyHocid(id);
                lichHoc lichHoc=new lichHoc();
                lichHoc.setThoiGianHoc(thoiGianHoc);
                lichHoc.setClassSchool(classSchool);
                lichHocService.save(lichHoc);
                return "redirect:/class/showHomeClass";
            }

    }
    @PostMapping("/update-teacher")
    public String updateTeacher(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") String id,Model model){
        staff teacher=teacherService.findTeacherByid(id);
        classSchool.setHomeroomTeacher(teacher);
        classService.update(classSchool);
        return "redirect:/class/showHomeClass";
    }
    @PostMapping("/update-subject")
    public String updateSubject(@ModelAttribute("classSchool") classSchool classSchool,@RequestParam("id") String id,Model model){
        subjects subjects=subjectService.findNSubjectByid(id);
        classSchool.setSubjects(subjects);
        classService.update(classSchool);
        return "redirect:/class/showHomeClass";
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("classSchool")ClassWeb classWeb, BindingResult result, Model model, HttpSession session){
        String className=classWeb.getClassName();
        //form validation
        if(result.hasErrors()){
            return "class/create";
        }
        //kieu tra classname ton tai
        classSchool classExisting=classService.findClassByName(className);
        if(classExisting!=null){
            model.addAttribute("ClassWeb",new classSchool());
            model.addAttribute("my_error","Lớp học đã tồn tại");
            return "class/create";
        }
        //neu chua ton tai

        classSchool classSchool=new classSchool();
        // Lấy thời gian hiện tại (ví dụ, làm chuỗi nó làm được)
        String timeCurrently = String.valueOf(System.currentTimeMillis());

// Tạo một số ngẫu nhiên
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
        String classid = "DH" + timeCurrently + randomNumber;
        classSchool.setEndDay(classWeb.getEndYear());
        classSchool.setSoTiet(classWeb.getSoTiet());
        classSchool.setSchoolYear(classWeb.getSchoolYear());
        classSchool.setPhongHoc(classWeb.getClassName());
        classService.save(classSchool);


        return "redirect:/class/showHomeClass";

    }


}
