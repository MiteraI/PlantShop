/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author Huynh Anh Kiet
 */
public class Plant {

    private int id;
    private String name;
    private double price;
    private String imgPath;
    private String description;
    private int status;
    private Category cate;

    public Plant() {
    }

    //Constructor for viewing order, description is not needed, status is not needed 
    //if status is false then user should not have been able to order in the first place
    public Plant(int id, String name, double price, String imgPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.status = status;
    }

    //Constructor for general purpose like homepage...
    public Plant(int id, String name, double price, String imgPath, String description, int status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
    }

    //Constructor for editing plant table for admin
    public Plant(int id, String name, double price, String imgPath, String description, int status, Category cate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.cate = cate;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    public String getStatusInString() {
        switch (this.status) {
            case 0:
                return "Out of Stock";
            case 1:
                return "In Stock";
        }
        return "";
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the Cate
     */
    public Category getCate() {
        return cate;
    }

    /**
     * @param CateID the CateID to set
     */
    public void setCate(Category cate) {
        this.cate = cate;
    }

}
