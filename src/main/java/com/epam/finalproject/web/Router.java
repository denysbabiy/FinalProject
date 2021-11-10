package com.epam.finalproject.web;

public class Router {
    public enum TransactionType{
        FORWARD,REDIRECT
    }
    private TransactionType type = TransactionType.FORWARD;
    private String currentPage = "/index.jsp";

    public String getPage() {
        return currentPage;
    }

    public void setPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
