package org.launchcode.meander.models;


import javax.persistence.*;

@Entity
@Table(name = "Image_Upload_Model")
public class ImageUploadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String type;

    @Column(length = 1073741824) // 1gb = 1073741824 bytes
    private byte[] imageByte; //ByteArray wraps java byte arrays (byte[]) to allow byte arrays to be used as keys in a hashtable. This is required because the equals function on byte[] directly uses reference equality.

    public ImageUploadModel() {
//Empty constructor
    }

    public ImageUploadModel(String name, String type, byte[] imageByte) { //Constructor to assign values
        this.name = name;
        this.type = type;
        this.imageByte = imageByte;
    }

    //Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public byte[] getImageByte() { return imageByte; }
    public void setImageByte(byte[] imageByte) {this.imageByte = imageByte;}
}



