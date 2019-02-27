package model;

public class BookInfo {

    private int bookInfoId;
    private String title;
    private String bookDescription;

    public BookInfo(int bookInfoId, String title, String bookDescription) {
        this.bookInfoId = bookInfoId;
        this.title = title;
        this.bookDescription = bookDescription;
    }

    public BookInfo(String title, String bookDescription) {
        this.title = title;
        this.bookDescription = bookDescription;
    }

    public int getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(int bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
