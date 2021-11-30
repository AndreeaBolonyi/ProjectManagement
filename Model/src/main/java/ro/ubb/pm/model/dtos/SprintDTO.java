package ro.ubb.pm.model.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SprintDTO {

    private Integer id;

    private Integer epicId;

    @NotNull
    private String title;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
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
}
