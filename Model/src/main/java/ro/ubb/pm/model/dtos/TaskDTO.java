package ro.ubb.pm.model.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TaskDTO {

    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Integer assignedToId;

    @NotNull
    private Integer createdById;

    @NotNull
    private LocalDate created;

    private Integer userStoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Integer assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(Integer userStoryId) {
        this.userStoryId = userStoryId;
    }
}
