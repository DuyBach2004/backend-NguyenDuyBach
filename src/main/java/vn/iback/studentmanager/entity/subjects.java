package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class subjects {
    @Id
    @Column(name = "subject_id",length = 50)
    private String subject_id;
    @Column(name = "number_of_credits")
    private int numberOfCredits;
    @Column(name = "subject_name",length = 100)
    private String subjectName;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "specialized_name")
    private specialization maChuyenNghanh;

    public subjects() {
    }

    public subjects(String subject_id, int numberOfCredits, String subjectName, specialization maChuyenNghanh) {
        this.subject_id = subject_id;
        this.numberOfCredits = numberOfCredits;
        this.subjectName = subjectName;
        this.maChuyenNghanh = maChuyenNghanh;
    }

    public specialization getMaChuyenNghanh() {
        return maChuyenNghanh;
    }

    public void setMaChuyenNghanh(specialization maChuyenNghanh) {
        this.maChuyenNghanh = maChuyenNghanh;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subbject_id) {
        this.subject_id = subbject_id;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return subjectName;
    }

}
