package vn.iback.studentmanager.Controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.dto.DataMailDTO;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.mailService.MailService;
import vn.iback.studentmanager.service.nootebookService;
import vn.iback.studentmanager.service.studentService;
import vn.iback.studentmanager.utils.Const;

import java.util.*;

@Controller
@RequestMapping("/diem")
public class DiemController {
    private diemService diemService;
    private nootebookService nootebookService;
    private Userservice userservice;
    private vn.iback.studentmanager.service.studentService studentService;
    private ketQuaHocTapService ketQuaHocTapService;
    private MailService mailService;
    @Autowired
    public DiemController(vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.nootebookService nootebookService, Userservice userservice, vn.iback.studentmanager.service.studentService studentService, vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, MailService mailService) {
        this.diemService = diemService;
        this.nootebookService = nootebookService;
        this.userservice = userservice;
        this.studentService = studentService;
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.mailService = mailService;
    }

    @GetMapping("/showPageDiemAdmin")
    public String showPageDiemAdmin(Model model){
        List<diem> diems=diemService.findAllDiem();
        model.addAttribute("diems",diems);
        return "diem/home1";

    }
    @GetMapping("/showPageDiem")
    public String showPageDiem(@RequestParam("username") String username ,Model model){
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
    @GetMapping("delete")
    public String deleteDiem(@RequestParam("id") int id,Model model){
        diemService.deleteById(id);
        return "redirect:/diem/showPageDiem";
    }
    @GetMapping("/showPageUpdate")
    public String showPageUpdate(@RequestParam("id") int id,Model model){
        diem diem=diemService.findDiemById(id);
        model.addAttribute("diem",diem);
        return "diem/update";
    }
    @GetMapping("/sendmail")
    public String sendMail(@ModelAttribute("user") user user,Model model) throws MessagingException {
       try {
           DataMailDTO dataMailDTO=new DataMailDTO();
           dataMailDTO.setTo(user.getEmail());
           dataMailDTO.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
           Map<String, Object> props = new HashMap<>();
           props.put("name", user.getFirstname()+' '+user.getLastname());
           props.put("username", user.getUsername());
           dataMailDTO.setProps(props);
           mailService.sendHtmlMail(dataMailDTO, Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
       }catch (MessagingException exception){
           exception.printStackTrace();
       }

       return "xacthucemail";
    }

    @PostMapping("/update")
    public String updateDiem(@ModelAttribute("diem") diem diem,Model model){
        double diemTongKet=(diem.getDiemChuyenCan()*20+diem.getDiemGiuaKi()*30+diem.getDiemCuoiKi()*50)/100;
        diem.setDiemTongKet(diemTongKet);
        diemService.update(diem);
        user user=userservice.findByUsername(diem.getNotebook().getStaff().getUsername());
        notebook notebook=nootebookService.findNotebookByid(diem.getNotebook().getId());
        System.out.println(diemTongKet);
        List<diem> diems=diemService.findAllDiem();
        double diemTb=0;
        double soTinChi=0;
        for (diem socre : diems) {
                    if (socre.getNotebook().getStaff().getUsername().equals(user.getUsername())){
                        diemTb=diemTb + socre.getDiemTongKet()*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
                        soTinChi=soTinChi + socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
//                        System.out.println(diemTongKet*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits());
                    }
        }
        double diemTbHocKy=diemTb/soTinChi;
        diemTbHocKy = Math.round(diemTbHocKy * 100.0) / 100.0;
//        System.out.println(diemTb);
//        System.out.println(soTinChi);
        student student=studentService.findStudentById(user.getStudentId().getStudentId());
        student.setDiemTbHocKy(diemTbHocKy);
        studentService.update(student);
        userservice.update(user);
        diemService.update(diem);

        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();

        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
        for (ketQuaHocTap ketQuaHocTap1 :ketQuaHocTapList) {
            if (ketQuaHocTap1.getUser().getUsername().equals(user.getUsername())){
                ketQuaHocTap1.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
                ketQuaHocTapService.update(ketQuaHocTap1);

                if(ketQuaHocTap1.getDiemTrungBinh()>=5 && ketQuaHocTap1.getDiemTrungBinh()<6.5 ){
                    ketQuaHocTap1.setKetQuaHocTap("Trung bình");
                }else if(ketQuaHocTap1.getDiemTrungBinh()>=6.5 && ketQuaHocTap1.getDiemTrungBinh()<8 ){
                    ketQuaHocTap1.setKetQuaHocTap("Khá");
                }else if(ketQuaHocTap1.getDiemTrungBinh()>=8 && ketQuaHocTap1.getDiemTrungBinh()<9 ){
                    ketQuaHocTap1.setKetQuaHocTap("Giỏi");
                }else if(ketQuaHocTap1.getDiemTrungBinh()>=9 && ketQuaHocTap1.getDiemTrungBinh()<=10 ){
                    ketQuaHocTap1.setKetQuaHocTap("Xuất sắc");
                }else if(ketQuaHocTap1.getDiemTrungBinh()>10){
                    ketQuaHocTap1.setKetQuaHocTap("Lỗi");
                }else if(ketQuaHocTap1.getDiemTrungBinh()>=4 && ketQuaHocTap1.getDiemTrungBinh()<5 ){
                    ketQuaHocTap1.setKetQuaHocTap("Yếu");
                }else{
                    ketQuaHocTap1.setKetQuaHocTap("Kém");
                }
                ketQuaHocTapService.update(ketQuaHocTap1);
            }else {

                ketQuaHocTap.setUser(user);
                ketQuaHocTap.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
                ketQuaHocTap.setKyHoc(diem.getNotebook().getClassSchool().getKyHoc());
                ketQuaHocTapService.update(ketQuaHocTap);
            }
        }


        if(ketQuaHocTap.getDiemTrungBinh()>=5 && ketQuaHocTap.getDiemTrungBinh()<6.5 ){
            ketQuaHocTap.setKetQuaHocTap("Trung bình");
        }else if(ketQuaHocTap.getDiemTrungBinh()>=6.5 && ketQuaHocTap.getDiemTrungBinh()<8 ){
            ketQuaHocTap.setKetQuaHocTap("Khá");
        }else if(ketQuaHocTap.getDiemTrungBinh()>=8 && ketQuaHocTap.getDiemTrungBinh()<9 ){
            ketQuaHocTap.setKetQuaHocTap("Giỏi");
        }else if(ketQuaHocTap.getDiemTrungBinh()>=9 && ketQuaHocTap.getDiemTrungBinh()<=10 ){
            ketQuaHocTap.setKetQuaHocTap("Xuất sắc");
        }else if(ketQuaHocTap.getDiemTrungBinh()>10){
            ketQuaHocTap.setKetQuaHocTap("Lỗi");
        }else if(ketQuaHocTap.getDiemTrungBinh()>=4 && ketQuaHocTap.getDiemTrungBinh()<5 ){
            ketQuaHocTap.setKetQuaHocTap("Yếu");
        }else{
            ketQuaHocTap.setKetQuaHocTap("Kém");
        }
        ketQuaHocTapService.update(ketQuaHocTap);

        return "redirect:/diem/showPageDiemAdmin";
    }
    @PostMapping("/update-notebook")
    public String updateNoteBook(@ModelAttribute("diem") diem diem,@RequestParam("id") int id,Model model){
        notebook notebook=nootebookService.findNotebookByid(id);
        diem.setNotebook(notebook);
        diemService.update(diem);
        return "redirect:/diem/showPageDiem";
    }

    @GetMapping("/create")
    public String createDiem(Model model){
        diem diem=new diem();
        model.addAttribute("diem",diem);
        diem.setDiemChuyenCan(-1);
        diem.setDiemGiuaKi(-1);
        diem.setDiemCuoiKi(-1);
        diemService.save(diem);
        return "redirect:/diem/showPageDiemAdmin";
    }

}
