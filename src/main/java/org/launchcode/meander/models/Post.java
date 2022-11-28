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

    //still working on relationship between post and user
//    @OneToMany(mappedBy = "post")
//    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // creates a many-to-one relationship (many post, one user)
    @JoinTable(name = "Post_Images",
            joinColumns = {
            @JoinColumn(name = "Post_Id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "Image_Id")
            })
    private Set<ImageUploadModel> postImages; //binds the ImageUploadModel to the Post model and uses Set to account for if a user would like to upload multiple images for the same post

    public Set<ImageUploadModel> getPostImages() { return postImages; }
    public void setPostImages(Set<ImageUploadModel> postImages) { this.postImages = postImages; }

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
