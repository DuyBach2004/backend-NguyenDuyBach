package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class roles {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public roles() {
    }

    public roles(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
