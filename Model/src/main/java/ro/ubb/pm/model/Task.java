package ro.ubb.pm.model;

import java.time.LocalDate;

public class Task extends Entity{
    private String title;
    private String description;
    private User assignedTo;
    private User createdBy;
    private LocalDate created;
    private UserStory userStory;

    public Task() {}

    public Task(String title, String description, User assignedTo, User createdBy, LocalDate created, UserStory userStory) {
        this.title = title;
        this.description = description;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.created = created;
        this.userStory = userStory;
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

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public UserStory getUserStory() {
        return userStory;
    }

    public void setUserStory(UserStory userStory) {
        this.userStory = userStory;
    }
}
