package ro.ubb.pm.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "sprints")
public class Sprint extends Entity implements Serializable {

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER)
    private List<UserStory> userStories = new ArrayList<>();

    public Sprint() {}

    @Override
    public String toString() {
        return "Sprint{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userStories=" + userStories +
                '}';
    }

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
