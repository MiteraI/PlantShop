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
    private UOrder uOrder;
    private Plant plant;
    private int quantity;

    // Constructor
    public OrderDetail(int orderDetailID, UOrder uOrder, Plant plant, int quantity) {
        this.orderDetailID = orderDetailID;
        this.uOrder = uOrder;
        this.plant = plant;
        this.quantity = quantity;
    }

    // Special get function just for price 
    public double getPrice() {
        return plant.getPrice() * quantity;
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
     * @return the uOrder
     */
    public UOrder getuOrder() {
        return uOrder;
    }

    /**
     * @param uOrder the uOrder to set
     */
    public void setuOrder(UOrder uOrder) {
        this.uOrder = uOrder;
    }

    /**
     * @return the plant
     */
    public Plant getPlant() {
        return plant;
    }

    /**
     * @param plant the plant to set
     */
    public void setPlant(Plant plant) {
        this.plant = plant;
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

}
