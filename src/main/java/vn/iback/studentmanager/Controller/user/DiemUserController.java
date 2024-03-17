package vn.iback.studentmanager.Controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iback.studentmanager.entity.diem;
import vn.iback.studentmanager.entity.ketQuaHocTap;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.mailService.MailService;
import vn.iback.studentmanager.service.nootebookService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/diem-user")
public class DiemUserController {
    private vn.iback.studentmanager.service.diemService.diemService diemService;
    private vn.iback.studentmanager.service.nootebookService nootebookService;
    private Userservice userservice;
    private vn.iback.studentmanager.service.studentService studentService;
    private vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService;
    private MailService mailService;
    @Autowired
    public DiemUserController(vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.nootebookService nootebookService, Userservice userservice, vn.iback.studentmanager.service.studentService studentService, vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, MailService mailService) {
        this.diemService = diemService;
        this.nootebookService = nootebookService;
        this.userservice = userservice;
        this.studentService = studentService;
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.mailService = mailService;
    }
    @GetMapping("/showPageDiem")
    public String showPageDiem(@RequestParam("username") String username , Model model){
        List<diem> diems= diemService.findAllDiem();
        List<ketQuaHocTap> ketQua= ketQuaHocTapService.findAllKetQuaHocTap();
        for (ketQuaHocTap kq  :ketQua) {

            if(kq.getUser().getUsername().equals(username)){
                model.addAttribute("ketqua",kq);
                break;
            }
        }
        List<diem> diem2= new ArrayList<>();
        for (diem diem :diems) {

            if (diem.getNotebook().getStaff().getUsername().equals(username)){
                diem2.add(diem);
                model.addAttribute("diems",diem2);
                System.out.println(diem.getDiemCuoiKi());
            }

        }
        user user=userservice.findByUsername(username);
        model.addAttribute("user",user);

        return "diem/home";
    }
}
