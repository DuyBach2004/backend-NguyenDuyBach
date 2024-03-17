package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_into_notebook")
public class StudentIntoNotebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "notebook_id")
    private notebook notebook;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_username")
    private user user;

    @Column(name = "check_sign_up")
    private boolean checkSignUp;

    // Getter and Setter methods...


    public StudentIntoNotebook() {
    }

    public StudentIntoNotebook(int id, vn.iback.studentmanager.entity.notebook notebook, vn.iback.studentmanager.entity.user user, boolean checkSignUp) {
        this.id = id;
        this.notebook = notebook;
        this.user = user;
        this.checkSignUp = checkSignUp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public vn.iback.studentmanager.entity.notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(vn.iback.studentmanager.entity.notebook notebook) {
        this.notebook = notebook;
    }

    public vn.iback.studentmanager.entity.user getUser() {
        return user;
    }

    public void setUser(vn.iback.studentmanager.entity.user user) {
        this.user = user;
    }

    public boolean isCheckSignUp() {
        return checkSignUp;
    }

    public void setCheckSignUp(boolean checkSignUp) {
        this.checkSignUp = checkSignUp;
    }
}
