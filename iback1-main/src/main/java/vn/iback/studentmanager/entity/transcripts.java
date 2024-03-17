package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transcripts")
public class transcripts {
    @Id
    @Column(name = "transcripts_id")
    private String transcriptsId;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private student student;
    @Column(name = "conduct")
    private String conduct;
    @Column(name = "final_score")
    private double finalScore;
    @Column(name = "comment")
    private String  comment;

    public transcripts() {
    }

    public transcripts(String transcriptsId, vn.iback.studentmanager.entity.student student, String conduct, double finalScore, String comment) {
        this.transcriptsId = transcriptsId;
        this.student = student;
        this.conduct = conduct;
        this.finalScore = finalScore;
        this.comment = comment;
    }
}
