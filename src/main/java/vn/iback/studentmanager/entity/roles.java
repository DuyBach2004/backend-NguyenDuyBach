package vn.iback.studentmanager.entity;

import jakarta.persistence.*;

import java.security.Permission;
import java.util.Set;

@Entity
@Table(name="roles")
public class roles {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    Set<permission> permissions;
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

    public Set<permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return name;
    }
}
