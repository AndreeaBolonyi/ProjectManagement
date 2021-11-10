package ro.ubb.pm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sprint extends Entity{
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<UserStory> userStories = new ArrayList<>();

    public Sprint() {}

    public Sprint(String title, LocalDate startDate, LocalDate endDate, List<UserStory> userStories) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userStories = userStories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }
}
