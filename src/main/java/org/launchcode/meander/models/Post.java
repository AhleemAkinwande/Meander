package org.launchcode.meander.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Title is required.")
    @Size(min = 5, max = 20 )
    private String title;


    @Size(min = 5, max = 100 )
    private String text;


    public Post(String title, String postDetails) {
        this.title = title;
        text = postDetails;
    }

    public Post() {}

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
