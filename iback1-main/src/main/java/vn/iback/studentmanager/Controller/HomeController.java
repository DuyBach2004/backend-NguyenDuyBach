package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.StudentIntoNoteBookService;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.nootebookService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private classService classService;
    private nootebookService nootebookService;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private Userservice userservice;
    @Autowired
    public HomeController(vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.nootebookService nootebookService, StudentIntoNoteBookService studentIntoNoteBookService, Userservice userservice) {
        this.classService = classService;
        this.nootebookService = nootebookService;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.userservice = userservice;
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
        model.addAttribute("notebookList",notebookList);
        model.addAttribute("classSchoolList",classSchoolList);
        return "home";
    }



}
