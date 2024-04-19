package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.diemService.diemService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lophocphan")
public class LopHocPhanController {

    private nootebookService nootebookService;
    private vn.iback.studentmanager.service.teacherService teacherService;
    private classService classService;
    private subjectService subjectService;
    private Userservice userservice;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private diemService diemService;
    @Autowired
    public LopHocPhanController(vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.subjectService subjectService, Userservice userservice, StudentIntoNoteBookService studentIntoNoteBookService, vn.iback.studentmanager.service.diemService.diemService diemService) {
        this.nootebookService = nootebookService;
        this.teacherService = teacherService;
        this.classService = classService;
        this.subjectService = subjectService;
        this.userservice = userservice;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.diemService = diemService;
    }

//    @GetMapping("/showListNote-diem")
//    public String showHome(@RequestParam("id") int id ,Model model){
//        List<notebook> notebookList=nootebookService.findAllNote();
//        List<classSchool> classSchoolList=classService.findAllClass();
//        diem diem=diemService.findDiemById(id);
//        model.addAttribute("notebookList",notebookList);
//        model.addAttribute("classSchoolList",classSchoolList);
//        model.addAttribute("diem",diem);
//        return "home";
//    }


//    @PostMapping("/updateTeacher")
//    public String updateTeacher(@ModelAttribute("notebook") notebook notebook, @RequestParam("TeacherId") String id, Model model){
//        staff teacher= teacherService.findTeacherByid(id);
//        nootebookService.update(notebook);
//        return "redirect:/home";
//    }
//    @GetMapping("/studentEnroll")
//    public String studentErroll(@RequestParam("id") String id, @RequestParam("username") String username, Model model) {
//        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(username)) {
//            // Xử lý khi id hoặc username không hợp lệ
//            return "redirect:/error";
//        }
//
//        // Tiếp tục xử lý khi id và username hợp lệ
//        notebook notebook = nootebookService.findNotebookByid(Integer.parseInt(id));
//        user user = userservice.findByUsername(username);
//        classSchool classSchool=classService.findClassByid(notebook.getClassSchool().getIdClass());
//
//        if (notebook != null && user != null) {
//            Collection<user> users = new ArrayList<>();
//            users.add(user);
////            classSchool.setUsers(users);
////            notebook.setQuantity(1);
////            notebook.setStaff(user);
////            nootebookService.update(notebook);
////            StudentIntoNotebook studentIntoNotebook=new StudentIntoNotebook();
////            studentIntoNotebook.setNotebook(notebook);
////            studentIntoNotebook.setUser(user);
////            studentIntoNotebook.setCheckSignUp(true);
////            studentIntoNoteBookService.save(studentIntoNotebook);
////            model.addAttribute("studentIntoNotebook",studentIntoNotebook);
//
//        } else {
//            // Xử lý khi notebook hoặc user không tồn tại
//            return "redirect:/error";
//        }
//
//        return "redirect:/home";
//    }
//    @GetMapping("/studentCancel")
//    public String studentstudentCancel(@RequestParam("id") String id,@RequestParam("username") String username ,Model model, RedirectAttributes redirectAttributes) {
//
//
//        // Tiếp tục xử lý khi id và username hợp lệ
//        notebook notebook = nootebookService.findNotebookByid(Integer.parseInt(id));
//
//        nootebookService.deleteById(Integer.parseInt(id));
//        redirectAttributes.addAttribute("username", username);
//        return "redirect:/homeUser/showHome";
//    }

//    @PostMapping("/updateClass")
//    public String updateClass(@ModelAttribute("notebook") notebook notebook, @RequestParam("ClassId") String id, Model model){
////        classSchool classSchool= classService.findClassByid(id);
////        notebook.setClassSchool(classSchool);
//        nootebookService.update(notebook);
//        return "redirect:/home";
//    }
//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") int id, Model model){
//        nootebookService.deleteById(id);
//        return "redirect:/home/showHome";
//    }
@GetMapping("")
public List<notebook> showPageClassApi(Model model){
    List<notebook> notebookList = nootebookService.findAllNote();
    if (notebookList == null){
        return null;
    }else {
        return notebookList;
    }
}

//    @GetMapping("/ma-lop-hoc")
//    public List<StudentIntoNotebook> danhsachlophoc(@RequestParam("username") String username){
//        List<StudentIntoNotebook> studentIntoNotebooks = studentIntoNoteBookService.findAllStudentIntoNotebook();
//        List<StudentIntoNotebook> studentIntoNotebooks1 = new ArrayList<>();
//        for (StudentIntoNotebook studentIntoNotebook : studentIntoNotebooks) {
//            if(studentIntoNotebook.getUser().getUsername().equals(username)){
//                studentIntoNotebooks1.add(studentIntoNotebook);
//            }
//        }
//        return studentIntoNotebooks1;
//    }
    @GetMapping("/ma-lop-hoc-phan")
public notebook showHomeApi(@RequestParam("Mã lớp học phần") int id){
    notebook notebook=nootebookService.findNotebookByid(id);
    if (notebook== null){
        return null;
    }else {
        return notebook;
    }
}
    @DeleteMapping("/ma-lop-hoc-phan")
    public ResponseEntity deleteLichApi(@RequestParam int id){
        notebook existing = nootebookService.findNotebookByid(id);
        if (existing != null){
            nootebookService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-lop-hoc-phan")
    public ResponseEntity updateNotebook(@RequestParam int id, @RequestBody notebook notebook, Model model){
        notebook notebook1 = nootebookService.findNotebookByid(id);
        try {
            if (notebook1 != null){
                notebook1.setClassSchool(notebook.getClassSchool());
                notebook1.setStaff(notebook.getStaff());
                notebook1.setDiems(notebook.getDiems());
                notebook1.setTeachingDay(notebook.getTeachingDay());
                nootebookService.update(notebook1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createNotebookApi(@RequestParam("Mã lớp học") int id, @RequestParam("username giảng viên") String username, @RequestParam("Ngày dạy")Date day){
        notebook notebook = new notebook();
        classSchool classSchool = classService.findClassByid(id);
        user user = userservice.findByUsername(username);
        if (user == null || classSchool == null){
            return new ResponseEntity<>("Error: Class or username does not exist", HttpStatus.BAD_REQUEST);
        }else {
            notebook.setStaff(user);
            notebook.setClassSchool(classSchool);
            notebook.setTeachingDay(day);
            nootebookService.save(notebook);
            return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
        }
    }
//@GetMapping("/showListLopHocPhan")
//public List<notebook> showHomeApi(){
//    List<notebook> notebookList=nootebookService.findAllNote();
//    if (notebookList== null){
//        return null;
//    }else {
//        return notebookList;
//    }
//}
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteLichApi(@PathVariable int id, Model model){
//        notebook existing=nootebookService.findNotebookByid(id);
//        if (existing!=null){
//            nootebookService.deleteById(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PostMapping("/create-rest-api")
//    public ResponseEntity<String> createNotebookApi(@RequestParam("Mã lớp học phần") int id, @RequestParam("username giáo viên") String username, @RequestParam("Ngày dạy")Date day){
//        notebook notebook=new notebook();
//        classSchool classSchool=classService.findClassByid(id);
//        user user=userservice.findByUsername(username);
//        if (user == null || classSchool == null){
//            return new ResponseEntity<>("Error: Class or username does not exist", HttpStatus.BAD_REQUEST);
//        }else {
//            notebook.setStaff(user);
//            notebook.setClassSchool(classSchool);
//            notebook.setTeachingDay(day);
//            nootebookService.save(notebook);
//            return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
//        }
//    }
//    @GetMapping("/username")
//    public List<StudentIntoNotebook> danhsachlophoc(@RequestParam("username") String username){
//        List<StudentIntoNotebook> studentIntoNotebooks=studentIntoNoteBookService.findAllStudentIntoNotebook();
//        List<StudentIntoNotebook> studentIntoNotebooks1=new ArrayList<>();
//        for (StudentIntoNotebook studentIntoNotebook:studentIntoNotebooks) {
//            if(studentIntoNotebook.getUser().getUsername().equals(username)){
//                studentIntoNotebooks1.add(studentIntoNotebook);
//            }
//        }
//        return studentIntoNotebooks1;
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public notebook updateKhoa(@PathVariable int id,@RequestBody notebook notebook,Model model){
//        notebook notebook1=nootebookService.findNotebookByid(id);
//        notebook1.setClassSchool(notebook.getClassSchool());
//        notebook1.setStaff(notebook.getStaff());
//        notebook1.setDiems(notebook.getDiems());
//        notebook1.setTeachingDay(notebook.getTeachingDay());
//        nootebookService.update(notebook1);
//        return notebook1;
//    }
//    @GetMapping("/create")
//    public String createNotebook(@RequestParam("username") String username ,Model model,RedirectAttributes redirectAttributes){
//        notebook notebook=new notebook();
//        nootebookService.save(notebook);
//        redirectAttributes.addAttribute("username", username);
//        return "redirect:/home";
//    }

//    @GetMapping("/updateCredits")
//    public String updateCredits(@RequestParam("id") String id,@RequestParam("notebookid") String notebookId,Model model){
//        notebook notebook=nootebookService.findNotebookByid(Integer.parseInt(notebookId));
//        subjects subjects=subjectService.findNSubjectByid(notebook.getSubjects().getSubject_id());
//        if(id.equals("1")){
//            notebook.setNumberOfTeachingPeriods(1);
//            subjects.setNumberOfCredits(1);
//        } else if (id.equals("2")) {
//            notebook.setNumberOfTeachingPeriods(2);
//            subjects.setNumberOfCredits(2);
//        }else if (id.equals("3")) {
//            notebook.setNumberOfTeachingPeriods(3);
//            subjects.setNumberOfCredits(3);
//        }else if (id.equals("4")) {
//            notebook.setNumberOfTeachingPeriods(4);
//            subjects.setNumberOfCredits(4);
//        }
//        nootebookService.update(notebook);
//        return "redirect:/home";
//    }

}
