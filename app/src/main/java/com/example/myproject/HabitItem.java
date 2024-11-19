package com.example.myproject;

public class HabitItem {
    int habitIcon;
    String habitTitle, habitDescription;

    public HabitItem(int habitIcon, String habitTitle, String habitDescription) {
        this.habitIcon = habitIcon;
        this.habitTitle = habitTitle;
        this.habitDescription = habitDescription;
    }

    public int getHabitIcon() {
        return habitIcon;
    }

    public void setHabitIcon(int habitIcon) {
        this.habitIcon = habitIcon;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
        this.habitTitle = habitTitle;
    }

    public String getHabitDescription() {
        return habitDescription;
    }

    public void setHabitDescription(String habitDescription) {
        this.habitDescription = habitDescription;
    }
}
