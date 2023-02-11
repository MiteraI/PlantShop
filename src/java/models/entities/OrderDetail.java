/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author Huynh Anh Kiet
 */
public class OrderDetail {

    private int orderDetailID;
    private int orderID;
    private int plantID;
    private String plantName;
    private double price;
    private String imgPath;
    private int quantity;
    private String orderDate;
    private String shippingDate;
    // Constructor
    public OrderDetail(int orderDetailID, int orderID, int plantID, String plantName, double price, String imgPath, int quantity, String orderDate, String shippingDate) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.plantID = plantID;
        this.plantName = plantName;
        this.price = price;
        this.imgPath = imgPath;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
    }

    /**
     * @return the orderDetailID
     */
    public int getOrderDetailID() {
        return orderDetailID;
    }

    /**
     * @param orderDetailID the orderDetailID to set
     */
    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the plantID
     */
    public int getPlantID() {
        return plantID;
    }

    /**
     * @param plantID the plantID to set
     */
    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    /**
     * @return the plantName
     */
    public String getPlantName() {
        return plantName;
    }

    /**
     * @param plantName the plantName to set
     */
    public void setPlantName(String plantName) {
        this.plantName = plantName;
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
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the shippingDate
     */
    public String getShippingDate() {
        return shippingDate;
    }

    /**
     * @param shippingDate the shippingDate to set
     */
    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }
}
