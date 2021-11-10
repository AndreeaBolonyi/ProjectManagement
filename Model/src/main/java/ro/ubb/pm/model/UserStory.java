package ro.ubb.pm.model;

import ro.ubb.pm.model.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserStory extends Entity{
    private String title;
    private String description;
    private User assignedTo;
    private User createdBy;
    private Status status;
    private LocalDate created;
    private Epic epic;
    private List<Task> tasks = new ArrayList<>();

    public UserStory() {}

    public UserStory(String title, String description, User assignedUser, User createdByUser, Status status, LocalDate created, Epic epic, List<Task> tasks) {
        this.title = title;
        this.description = description;
        this.assignedTo = assignedUser;
        this.createdBy = createdByUser;
        this.status = status;
        this.created = created;
        this.epic = epic;
        this.tasks = tasks;
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

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assigned) {
        this.assignedTo= assigned;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
