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


   @Column(length = 50, nullable=false)
   @NotBlank(message = "Title is required.")
   @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters.")
   private String title;


    @Column(length = 2000, nullable=false)
    @NotBlank(message = "Text is required.")
    @Size(min = 5, max = 2000, message = "Post must be longer than 5 characters and fewer than 1000 characters.")
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

    public String getShortPostSnippet() {
        if(this.text.length() < 50) {
            return this.text.substring(0, this.text.length());
        } else {
            return this.text.substring(0, 49) + "...";

        }
    }

    public String getLongPostSnippet() {
        if(this.text.length() < 150) {
            return this.text.substring(0, this.text.length());
        } else {
            return this.text.substring(0, 145) + "...";

        }
    }
}
