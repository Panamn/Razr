package com.example.lb38;

public class User {

    private String userName;
    private String userMessage;
    private int userImageRes;

    public User(String userName, String userMessage, int userImageRes){
        this.userName = userName;
        this.userMessage = userMessage;
        this.userImageRes = userImageRes;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public int getUserImageRes() {
        return userImageRes;
    }

    public void setUserImageRes(int userImageRes) {
        this.userImageRes = userImageRes;
    }


}
