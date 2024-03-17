package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name=" report_card")
public class report_card {
    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private subjects subjects;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private student student;
    @Column(name = "test_name")
    private String testName;
    @Column(name = "test_day")
    private String testDay;
    @Column(name = "score")
    private double score;

    public report_card() {
    }

    public report_card(subjects subjects, vn.iback.studentmanager.entity.student student, String testName, String testDay, double score) {
        this.subjects = subjects;
        this.student = student;
        this.testName = testName;
        this.testDay = testDay;
        this.score = score;
    }

    public subjects getSubject() {
        return subjects;
    }

    public void setSubject(subjects subjects) {
        this.subjects = subjects;
    }

    public vn.iback.studentmanager.entity.student getStudent() {
        return student;
    }

    public void setStudent(vn.iback.studentmanager.entity.student student) {
        this.student = student;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDay() {
        return testDay;
    }

    public void setTestDay(String testDay) {
        this.testDay = testDay;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
