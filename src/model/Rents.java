package model;

import java.math.BigDecimal;
import java.util.Date;

public class Rents {

    private int rentsId;
    private String rentedDate;
    private String returnDate;
    private int rentedTo;
    private int rentCopyId;
    private String status;
    private BigDecimal totalPrice;

    public Rents(int rentsId, String rentedDate, String returnDate, int rentedTo, int rentCopyId, String status, BigDecimal totalPrice) {
        this.rentsId = rentsId;
        this.rentedDate = rentedDate;
        this.returnDate = returnDate;
        this.rentedTo = rentedTo;
        this.rentCopyId = rentCopyId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Rents(String rentedDate, String returnDate, int rentedTo, int rentCopyId, String status, BigDecimal totalPrice) {
        this.rentedDate = rentedDate;
        this.returnDate = returnDate;
        this.rentedTo = rentedTo;
        this.rentCopyId = rentCopyId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getRentsId() {
        return rentsId;
    }

    public void setRentsId(int rentsId) {
        this.rentsId = rentsId;
    }

    public String getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(String rentedDate) {
        this.rentedDate = rentedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(int rentedTo) {
        this.rentedTo = rentedTo;
    }

    public int getRentCopyId() {
        return rentCopyId;
    }

    public void setRentCopyId(int rentCopyId) {
        this.rentCopyId = rentCopyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
