package ro.ubb.pm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Entity{
    private String title;
    private LocalDate created;
    private Project project;
    private List<UserStory> userStories = new ArrayList<>();

    public Epic() {}

    public Epic(String title, LocalDate created, Project project, List<UserStory> userStories) {
        this.title = title;
        this.created = created;
        this.project = project;
        this.userStories = userStories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
