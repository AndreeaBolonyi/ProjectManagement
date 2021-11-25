package ro.ubb.pm.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@javax.persistence.Entity
@Table(name = "roles")
public class Role extends Entity {

    @NotNull
    @Column(name = "title")
    private String title;

    public Role() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
