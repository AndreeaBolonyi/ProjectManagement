package ro.ubb.pm.model;

public class Role extends Entity{
    private String title;

    public Role() {}

    public Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
