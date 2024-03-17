package vn.iback.studentmanager.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "specialization")
public class specialization {
    @Id
    @Column(name="specialized_code")
    private String specializedCode;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ma_khoa")
    private khoa maKhoa;
    @Column(name = "specialized_name")
    private String specializedName;

    @Column(name = "number_of_credits")
    private int numberOfCredits;

    public specialization() {
    }

    public specialization(String specializedCode, khoa maKhoa, String specializedName, int numberOfCredits) {
        this.specializedCode = specializedCode;
        this.maKhoa = maKhoa;
        this.specializedName = specializedName;
        this.numberOfCredits = numberOfCredits;
    }

    public khoa getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(khoa maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getSpecializedCode() {
        return specializedCode;
    }

    public void setSpecializedCode(String specializedCode) {
        this.specializedCode = specializedCode;
    }

    public String getSpecializedName() {
        return specializedName;
    }

    public void setSpecializedName(String specializedName) {
        this.specializedName = specializedName;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }
}
