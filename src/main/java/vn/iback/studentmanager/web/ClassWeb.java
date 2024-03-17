package vn.iback.studentmanager.web;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class ClassWeb {
    @NotNull(message = "thông tin bắt buộc")
    @Size(min=1, message = "độ dài tối thiểu là 1")
    private String className;
    private Date schoolYear;
    private Date endYear;
    private int soTiet;

    public ClassWeb(String className, Date schoolYear, Date endYear, int soTiet) {
        this.className = className;
        this.schoolYear = schoolYear;
        this.endYear = endYear;
        this.soTiet = soTiet;
    }

    public Date getEndYear() {
        return endYear;
    }

    public void setEndYear(Date endYear) {
        this.endYear = endYear;
    }

    public ClassWeb() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Date schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public void setSoTiet(int soTiet) {
        this.soTiet = soTiet;
    }
}
