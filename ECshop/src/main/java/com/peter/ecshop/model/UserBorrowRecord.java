package com.peter.ecshop.model;

import java.time.LocalDate;

public class UserBorrowRecord {
    private Integer userId;
    private Integer bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;

    public UserBorrowRecord() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;

        if (this.dueDate == null && borrowDate != null) {
            this.dueDate = borrowDate.plusDays(30);
        }
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
