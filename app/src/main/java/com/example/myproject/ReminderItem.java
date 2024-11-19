package com.example.myproject;

public class ReminderItem {
    String remainderPriotiry, remainderTitle, remainderTime;
    boolean checkBox;

    public ReminderItem(String remainderPriotiry, String remainderTitle, String remainderTime, boolean checkBox) {
        this.remainderPriotiry = remainderPriotiry;
        this.remainderTitle = remainderTitle;
        this.remainderTime = remainderTime;
        this.checkBox = checkBox;
    }

    public String getRemainderPriotiry() {
        return remainderPriotiry;
    }

    public void setRemainderPriotiry(String remainderPriotiry) {
        this.remainderPriotiry = remainderPriotiry;
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
