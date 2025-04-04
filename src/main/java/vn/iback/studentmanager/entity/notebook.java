package vn.iback.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "notebook")
public class notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    @JsonManagedReference
    private user staff;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class")
    @JsonBackReference
    private classSchool classSchool;
    @Column(name = "teaching_day")
    private Date teachingDay;
    @OneToMany(mappedBy = "notebook", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @Column(name = "diem")
    @JsonIgnore
    private List<diem> diems;


    public notebook() {
    }

    public notebook(int id, user staff, vn.iback.studentmanager.entity.classSchool classSchool, Date teachingDay, List<diem> diems) {
        this.id = id;
        this.staff = staff;
        this.classSchool = classSchool;
        this.teachingDay = teachingDay;
        this.diems = diems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public user getStaff() {
        return staff;
    }

    public void setStaff(user staff) {
        this.staff = staff;
    }

    public vn.iback.studentmanager.entity.classSchool getClassSchool() {
        return classSchool;
    }

    public void setClassSchool(vn.iback.studentmanager.entity.classSchool classSchool) {
        this.classSchool = classSchool;
    }

    public Date getTeachingDay() {
        return teachingDay;
    }

    public void setTeachingDay(Date teachingDay) {
        this.teachingDay = teachingDay;
    }

    public List<diem> getDiems() {
        return diems;
    }

    public void setDiems(List<diem> diems) {
        this.diems = diems;
    }
}
