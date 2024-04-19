package vn.iback.studentmanager.Controller;

import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iback.studentmanager.dto.DataMailDTO;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.mailService.MailService;
import vn.iback.studentmanager.service.nootebookService;
import vn.iback.studentmanager.service.studentService;
import vn.iback.studentmanager.service.teacherService;
import vn.iback.studentmanager.utils.Const;

import java.util.*;

@RestController
@RequestMapping("/diem")
public class DiemController {
    private diemService diemService;
    private nootebookService nootebookService;
    private Userservice userservice;
    private vn.iback.studentmanager.service.studentService studentService;
    private ketQuaHocTapService ketQuaHocTapService;
    private MailService mailService;
    private teacherService teacherService;
    @Autowired
    public DiemController(vn.iback.studentmanager.service.diemService.diemService diemService, vn.iback.studentmanager.service.nootebookService nootebookService, Userservice userservice, vn.iback.studentmanager.service.studentService studentService, vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, MailService mailService, vn.iback.studentmanager.service.teacherService teacherService) {
        this.diemService = diemService;
        this.nootebookService = nootebookService;
        this.userservice = userservice;
        this.studentService = studentService;
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.mailService = mailService;
        this.teacherService = teacherService;
    }
    @GetMapping("")
    public List<diem> showPageClassApi(Model model){
        List<diem> diems = diemService.findAllDiem();
        return diems;
    }

    @GetMapping("/ma-bang-diem")
    public diem getById(@RequestParam int id){
        try {
            diem diem1 = diemService.findDiemById(id);
            if (diem1 != null){
                return diem1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy điểm với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-bang-diem")
    public ResponseEntity deleteDiemApi(@RequestParam int id){
        diem existing = diemService.findDiemById(id);
        if (existing != null){
            diemService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-bang-diem")
    public ResponseEntity updateDiem(@RequestParam int id, @RequestBody diem diem, Model model){
        diem diem1 = diemService.findDiemById(id);
        try {
            if (diem1 != null){
                diem1.setDiemChuyenCan(diem.getDiemChuyenCan());
                diem1.setDiemGiuaKi(diem.getDiemGiuaKi());
                diem1.setDiemCuoiKi(diem.getDiemCuoiKi());
                diemService.update(diem1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public diem createDiemApi(@RequestParam diem diem){
        diemService.save(diem);
        return diem;
    }
//    @GetMapping("/showPageDiemAdminApi")
//    public List<diem> showPageDiemAdminApi(){
//        List<diem> diems=diemService.findAllDiem();
//        return diems;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<Void> deleteDiemApi(@PathVariable int id, Model model){
//        diem existing= diemService.findDiemById(id);
//        if (existing!=null){
//            diemService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public classSchool updateApi(@PathVariable int id,@RequestBody classSchool classSchool,Model model){
//        diem diem=diemService.findDiemById(id);
//        double diemTongKet=(diem.getDiemChuyenCan()*20+diem.getDiemGiuaKi()*30+diem.getDiemCuoiKi()*50)/100;
//        diem.setDiemTongKet(diemTongKet);
//        diemService.update(diem);
//        user user=userservice.findByUsername(diem.getNotebook().getStaff().getUsername());
//        notebook notebook=nootebookService.findNotebookByid(diem.getNotebook().getId());
//        System.out.println(diemTongKet);
//        List<diem> diems=diemService.findAllDiem();
//        double diemTb=0;
//        double soTinChi=0;
//        for (diem socre : diems) {
//            if (socre.getNotebook() != null && socre.getNotebook().getStaff().getUsername().equals(user.getUsername())){
//                diemTb=diemTb + socre.getDiemTongKet()*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
//                soTinChi=soTinChi + socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
////                        System.out.println(diemTongKet*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits());
//            }
//        }
//        double diemTbHocKy=diemTb/soTinChi;
//        diemTbHocKy = Math.round(diemTbHocKy * 100.0) / 100.0;
////        System.out.println(diemTb);
////        System.out.println(soTinChi);
//        student student=studentService.findStudentById(user.getStudentId().getStudentId());
//        student.setDiemTbHocKy(diemTbHocKy);
//        studentService.update(student);
//        userservice.update(user);
//        diemService.update(diem);
//
//        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();
//
//        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
//        for (ketQuaHocTap ketQuaHocTap1 :ketQuaHocTapList) {
//            if (ketQuaHocTap1.getUser().getUsername().equals(user.getUsername())){
//                ketQuaHocTap1.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
//                ketQuaHocTapService.update(ketQuaHocTap1);
//
//                if(ketQuaHocTap1.getDiemTrungBinh()>=5 && ketQuaHocTap1.getDiemTrungBinh()<6.5 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Trung bình");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=6.5 && ketQuaHocTap1.getDiemTrungBinh()<8 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Khá");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=8 && ketQuaHocTap1.getDiemTrungBinh()<9 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Giỏi");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=9 && ketQuaHocTap1.getDiemTrungBinh()<=10 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Xuất sắc");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>10){
//                    ketQuaHocTap1.setKetQuaHocTap("Lỗi");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=4 && ketQuaHocTap1.getDiemTrungBinh()<5 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Yếu");
//                }else{
//                    ketQuaHocTap1.setKetQuaHocTap("Kém");
//                }
//                ketQuaHocTapService.update(ketQuaHocTap1);
//            }else {
//
//                ketQuaHocTap.setUser(user);
//                ketQuaHocTap.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
//                ketQuaHocTap.setKyHoc(diem.getNotebook().getClassSchool().getKyHoc());
//                ketQuaHocTapService.update(ketQuaHocTap);
//            }
//        }
//
//
//        if(ketQuaHocTap.getDiemTrungBinh()>=5 && ketQuaHocTap.getDiemTrungBinh()<6.5 ){
//            ketQuaHocTap.setKetQuaHocTap("Trung bình");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=6.5 && ketQuaHocTap.getDiemTrungBinh()<8 ){
//            ketQuaHocTap.setKetQuaHocTap("Khá");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=8 && ketQuaHocTap.getDiemTrungBinh()<9 ){
//            ketQuaHocTap.setKetQuaHocTap("Giỏi");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=9 && ketQuaHocTap.getDiemTrungBinh()<=10 ){
//            ketQuaHocTap.setKetQuaHocTap("Xuất sắc");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>10){
//            ketQuaHocTap.setKetQuaHocTap("Lỗi");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=4 && ketQuaHocTap.getDiemTrungBinh()<5 ){
//            ketQuaHocTap.setKetQuaHocTap("Yếu");
//        }else{
//            ketQuaHocTap.setKetQuaHocTap("Kém");
//        }
//        ketQuaHocTapService.update(ketQuaHocTap);
//
//        return classSchool;
//    }
//    @PostMapping("/create-rest-api")
//    public diem createDiemApi(){
//        diem diem=new diem();
//        diem.setDiemChuyenCan(-1);
//        diem.setDiemGiuaKi(-1);
//        diem.setDiemCuoiKi(-1);
//        diemService.save(diem);
//        return diem;
//    }



//    @GetMapping("/showPageDiemAdmin")
//    public String showPageDiemAdmin(Model model){
//        List<diem> diems=diemService.findAllDiem();
//        model.addAttribute("diems",diems);
//        return "diem/home1";
//
//    }

//    @GetMapping("/showPageDiem")
//    public String showPageDiem(@RequestParam("username") String username ,Model model){
//        List<diem> diems= diemService.findAllDiem();
//        List<ketQuaHocTap> ketQua= ketQuaHocTapService.findAllKetQuaHocTap();
//        for (ketQuaHocTap kq  :ketQua) {
//
//            if(kq.getUser().getUsername().equals(username)){
//                model.addAttribute("ketqua",kq);
//                break;
//            }
//        }
//            List<diem> diem2= new ArrayList<>();
//        for (diem diem :diems) {
//
//            if (diem.getNotebook().getStaff().getUsername().equals(username)){
//                diem2.add(diem);
//                model.addAttribute("diems",diem2);
//                System.out.println(diem.getDiemCuoiKi());
//            }
//
//        }
//        user user=userservice.findByUsername(username);
//        model.addAttribute("user",user);
//
//        return "diem/home";
//    }
//    @GetMapping("delete")
//    public String deleteDiem(@RequestParam("id") int id,Model model){
//        diemService.deleteById(id);
//        return "redirect:/home/showHome";
//    }

//    @GetMapping("/showPageUpdate")
//    public String showPageUpdate(@RequestParam("id") int id,Model model){
//        diem diem=diemService.findDiemById(id);
//        model.addAttribute("diem",diem);
//        return "diem/update";
//    }
//    @GetMapping("/sendmail")
//    public String sendMail(@ModelAttribute("user") user user,Model model) throws MessagingException {
//       try {
//           DataMailDTO dataMailDTO=new DataMailDTO();
//           dataMailDTO.setTo(user.getEmail());
//           dataMailDTO.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
//           Map<String, Object> props = new HashMap<>();
//           props.put("name", user.getFirstname()+' '+user.getLastname());
//           props.put("username", user.getUsername());
//           dataMailDTO.setProps(props);
//           mailService.sendHtmlMail(dataMailDTO, Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
//       }catch (MessagingException exception){
//           exception.printStackTrace();
//       }
//
//       return "xacthucemail";
//    }


//    @PostMapping("/update")
//    public String updateDiem(@ModelAttribute("diem") diem diem,Model model){
//        double diemTongKet=(diem.getDiemChuyenCan()*20+diem.getDiemGiuaKi()*30+diem.getDiemCuoiKi()*50)/100;
//        diem.setDiemTongKet(diemTongKet);
//        diemService.update(diem);
//        user user=userservice.findByUsername(diem.getNotebook().getStaff().getUsername());
//        notebook notebook=nootebookService.findNotebookByid(diem.getNotebook().getId());
//        System.out.println(diemTongKet);
//        List<diem> diems=diemService.findAllDiem();
//        double diemTb=0;
//        double soTinChi=0;
//        for (diem socre : diems) {
//                    if (socre.getNotebook() != null && socre.getNotebook().getStaff().getUsername().equals(user.getUsername())){
//                        diemTb=diemTb + socre.getDiemTongKet()*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
//                        soTinChi=soTinChi + socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits();
////                        System.out.println(diemTongKet*socre.getNotebook().getClassSchool().getSubjects().getNumberOfCredits());
//                    }
//        }
//        double diemTbHocKy=diemTb/soTinChi;
//        diemTbHocKy = Math.round(diemTbHocKy * 100.0) / 100.0;
////        System.out.println(diemTb);
////        System.out.println(soTinChi);
//        student student=studentService.findStudentById(user.getStudentId().getStudentId());
//        student.setDiemTbHocKy(diemTbHocKy);
//        studentService.update(student);
//        userservice.update(user);
//        diemService.update(diem);
//
//        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();
//
//        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
//        for (ketQuaHocTap ketQuaHocTap1 :ketQuaHocTapList) {
//            if (ketQuaHocTap1.getUser().getUsername().equals(user.getUsername())){
//                ketQuaHocTap1.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
//                ketQuaHocTapService.update(ketQuaHocTap1);
//
//                if(ketQuaHocTap1.getDiemTrungBinh()>=5 && ketQuaHocTap1.getDiemTrungBinh()<6.5 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Trung bình");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=6.5 && ketQuaHocTap1.getDiemTrungBinh()<8 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Khá");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=8 && ketQuaHocTap1.getDiemTrungBinh()<9 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Giỏi");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=9 && ketQuaHocTap1.getDiemTrungBinh()<=10 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Xuất sắc");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>10){
//                    ketQuaHocTap1.setKetQuaHocTap("Lỗi");
//                }else if(ketQuaHocTap1.getDiemTrungBinh()>=4 && ketQuaHocTap1.getDiemTrungBinh()<5 ){
//                    ketQuaHocTap1.setKetQuaHocTap("Yếu");
//                }else{
//                    ketQuaHocTap1.setKetQuaHocTap("Kém");
//                }
//                ketQuaHocTapService.update(ketQuaHocTap1);
//            }else {
//
//                ketQuaHocTap.setUser(user);
//                ketQuaHocTap.setDiemTrungBinh(user.getStudentId().getDiemTbHocKy());
//                ketQuaHocTap.setKyHoc(diem.getNotebook().getClassSchool().getKyHoc());
//                ketQuaHocTapService.update(ketQuaHocTap);
//            }
//        }
//
//
//        if(ketQuaHocTap.getDiemTrungBinh()>=5 && ketQuaHocTap.getDiemTrungBinh()<6.5 ){
//            ketQuaHocTap.setKetQuaHocTap("Trung bình");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=6.5 && ketQuaHocTap.getDiemTrungBinh()<8 ){
//            ketQuaHocTap.setKetQuaHocTap("Khá");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=8 && ketQuaHocTap.getDiemTrungBinh()<9 ){
//            ketQuaHocTap.setKetQuaHocTap("Giỏi");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=9 && ketQuaHocTap.getDiemTrungBinh()<=10 ){
//            ketQuaHocTap.setKetQuaHocTap("Xuất sắc");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>10){
//            ketQuaHocTap.setKetQuaHocTap("Lỗi");
//        }else if(ketQuaHocTap.getDiemTrungBinh()>=4 && ketQuaHocTap.getDiemTrungBinh()<5 ){
//            ketQuaHocTap.setKetQuaHocTap("Yếu");
//        }else{
//            ketQuaHocTap.setKetQuaHocTap("Kém");
//        }
//        ketQuaHocTapService.update(ketQuaHocTap);
//
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-notebook")
//    public String updateNoteBook(@RequestParam("selectedOption") int idNote,@RequestParam("idDiem") int id,Model model){
//        diem diem=diemService.findDiemById(id);
//        notebook notebook=nootebookService.findNotebookByid(idNote);
//        diem.setNotebook(notebook);
//        diemService.update(diem);
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-notebook")
//    public String updateNoteBook(@ModelAttribute("diem") diem diem,@RequestParam("id") int id,Model model){
//        notebook notebook=nootebookService.findNotebookByid(id);
//        diem.setNotebook(notebook);
//        diemService.update(diem);
//        return "redirect:/diem/showPageDiem";
//    }

//    @GetMapping("/create")
//    public String createDiem(Model model){
//        diem diem=new diem();
//        model.addAttribute("diem",diem);
//        diem.setDiemChuyenCan(-1);
//        diem.setDiemGiuaKi(-1);
//        diem.setDiemCuoiKi(-1);
//        diemService.save(diem);
//        return "redirect:/home/showHome";
//    }

}
