package model;

import java.math.BigDecimal;

public class BookCopy {

    private int bookCopyId;
    private boolean isRented;
    private int rentedTo;
    private int bookInfoId;
    private double pricePerDay;

    public BookCopy(int bookCopyId, boolean isRented, int rentedTo, int bookInfoId, double pricePerDay) {
        this.bookCopyId = bookCopyId;
        this.isRented = isRented;
        this.rentedTo = rentedTo;
        this.bookInfoId = bookInfoId;
        this.pricePerDay = pricePerDay;
    }

    public BookCopy(boolean isRented, int rentedTo, int bookInfoId, double pricePerDay) {
        this.isRented = isRented;
        this.rentedTo = rentedTo;
        this.bookInfoId = bookInfoId;
        this.pricePerDay = pricePerDay;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public int getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(int rentedTo) {
        this.rentedTo = rentedTo;
    }

    public int getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(int bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
