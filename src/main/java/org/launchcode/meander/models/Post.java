package org.launchcode.meander.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @NotNull
    private User user;

    public Post(String title, String postDetails,User user) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
