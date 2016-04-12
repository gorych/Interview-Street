package by.gstu.interviewstreet.domain;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "interview_types")
public class InterviewType implements Serializable {

    private static final int OPEN_TYPE_ID = 1;
    private static final String OPEN_TYPE_NAME = "open";

    @Id
    @Expose
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Expose
    @Column(name = "name")
    private String name;

    @Column(name = "rus_name")
    private String rusName;

    @Column(name = "icon")
    private String visibilityIcon;

    @Column(name = "title")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public boolean isOpen() {
        return OPEN_TYPE_NAME.equals(name) || OPEN_TYPE_ID == id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisibilityIcon() {
        return visibilityIcon;
    }

    public void setVisibilityIcon(String visibilityIcon) {
        this.visibilityIcon = visibilityIcon;
    }

    @Override
    public String toString() {
        return "InterviewType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
