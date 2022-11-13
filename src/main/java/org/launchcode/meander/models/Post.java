package org.launchcode.meander.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Post {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Title is required.")
    @Size(min = 5, max = 20 )
    private String title;


    @Size(min = 5, max = 100 )
    private String text;

    //still working on relationship between post and user
//    @OneToMany(mappedBy = "post")
//    private User user;


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
