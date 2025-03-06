package coursemanagement;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private int id;
    private String name;
    private String qualification;
    private String email;
    private List<Course> courses;

    public Faculty(int id, String name, String qualification, String email) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.email = email;
        this.courses = new ArrayList<>();
    }

    public void assignCourse(Course course) {
        courses.add(course);
    }

    public String getName() {
        return name;
    }
}
