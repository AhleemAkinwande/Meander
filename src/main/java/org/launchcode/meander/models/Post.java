package org.launchcode.meander.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
   @Size(min = 5, max = 20, message = "Title must be between 5 and 20 characters.")
    private String title;

   @NotBlank(message = "Text is required.")
   @Size(min = 5, max = 100, message = "Post must be longer than 5 characters.")
    private String text;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;



    public Post(String title, String postDetails, User user, Location location) {
        this.title = title;
        text = postDetails;
        this.user = user;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
