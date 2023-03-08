/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author Huynh Anh Kiet
 */
public class UOrder {

    private int uOrderID;
    private String orderDate;
    private String shipDate;
    private int status;
    private int accID;

    //Constructor for user
    public UOrder(int uOrderID, String orderDate, String shipDate, int status) {
        this.uOrderID = uOrderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
    }
    
    //Constructor for admin to view all order
    public UOrder(int uOrderID, String orderDate, String shipDate, int status, int accID) {
        this.uOrderID = uOrderID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accID = accID;
    }

    /**
     * @return the uOrderID
     */
    public int getuOrderID() {
        return uOrderID;
    }

    /**
     * @param uOrderID the uOrderID to set
     */
    public void setuOrderID(int uOrderID) {
        this.uOrderID = uOrderID;
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
     * @return the shipDate
     */
    public String getShipDate() {
        return shipDate;
    }

    /**
     * @param shipDate the shipDate to set
     */
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the accID
     */
    public int getAccID() {
        return accID;
    }

    /**
     * @param accID the accID to set
     */
    public void setAccID(int accID) {
        this.accID = accID;
    }
    public String getStatusInString() {
        switch(this.status) {
            case 1: return "Pending";
            case 2: return "Successful";
            case 3: return "Canceled";
        }
        return "";
    }
}
