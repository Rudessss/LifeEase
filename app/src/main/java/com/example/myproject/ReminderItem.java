package com.example.myproject;

public class ReminderItem {
    String remainderPriority, remainderTitle, remainderTime;
    boolean checkBox;

    public ReminderItem(String remainderPriority, String remainderTitle, String remainderTime, boolean checkBox) {
        this.remainderPriority = remainderPriority;
        this.remainderTitle = remainderTitle;
        this.remainderTime = remainderTime;
        this.checkBox = checkBox;
    }

    public String getRemainderPriority() {
        return remainderPriority;
    }

    public void setRemainderPriority(String remainderPriority) {
        this.remainderPriority = remainderPriority;
    }

    public String getRemainderTitle() {
        return remainderTitle;
    }

    public void setRemainderTitle(String remainderTitle) {
        this.remainderTitle = remainderTitle;
    }

    public String getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(String remainderTime) {
        this.remainderTime = remainderTime;
    }

    public boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
