package ro.ubb.pm.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends Entity{
    private String title;
    private List<Epic> epics = new ArrayList<>();

    public Project() {}

    public Project(String title, List<Epic> epics) {
        this.title = title;
        this.epics = epics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Epic> getEpics() {
        return epics;
    }

    public void setEpics(List<Epic> epics) {
        this.epics = epics;
    }
}
