package vn.iback.studentmanager.Controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iback.studentmanager.entity.notebook;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.diemService.diemService;

@Controller
@RequestMapping("/notebook-user")
public class NotebookUserController {

    private vn.iback.studentmanager.service.nootebookService nootebookService;
    private vn.iback.studentmanager.service.teacherService teacherService;
    private vn.iback.studentmanager.service.classService classService;
    private vn.iback.studentmanager.service.subjectService subjectService;
    private Userservice userservice;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private vn.iback.studentmanager.service.diemService.diemService diemService;
    @Autowired
    public NotebookUserController(vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.subjectService subjectService, Userservice userservice, StudentIntoNoteBookService studentIntoNoteBookService, vn.iback.studentmanager.service.diemService.diemService diemService) {
        this.nootebookService = nootebookService;
        this.teacherService = teacherService;
        this.classService = classService;
        this.subjectService = subjectService;
        this.userservice = userservice;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.diemService = diemService;
    }
    @GetMapping("/studentCancel")
    public String studentstudentCancel(@RequestParam("id") String id, @RequestParam("username") String username , Model model, RedirectAttributes redirectAttributes) {


        // Tiếp tục xử lý khi id và username hợp lệ
        notebook notebook = nootebookService.findNotebookByid(Integer.parseInt(id));

        nootebookService.deleteById(Integer.parseInt(id));
        redirectAttributes.addAttribute("username", username);
        return "redirect:/homeUser/showHome";
    }
}
