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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookInfo bookInfo = (BookInfo) o;

        if (bookInfoId != bookInfo.bookInfoId) return false;
        if (title != null ? !title.equals(bookInfo.title) : bookInfo.title != null) return false;
        return bookDescription != null ? bookDescription.equals(bookInfo.bookDescription) : bookInfo.bookDescription == null;
    }

    @Override
    public int hashCode() {
        int result = bookInfoId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (bookDescription != null ? bookDescription.hashCode() : 0);
        return result;
    }
}
