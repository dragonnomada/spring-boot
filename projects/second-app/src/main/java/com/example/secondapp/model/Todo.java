package com.example.secondapp.model;

import java.util.Date;

public class Todo {
    
    private final int id;
    private final String title;
    private final boolean checked;
    private final Date createAt;
    private final Date updateAt;

    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
        this.checked = false;
        this.createAt = new Date();
        this.updateAt = new Date();
    }

    public Todo(int id, String title, boolean checked, Date createAt, Date updateAt) {
        this.id = id;
        this.title = title;
        this.checked = checked;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public Date getCreateAt() {
        return this.createAt;
    }

    public Date getUpdateAt() {
        return this.updateAt;
    }



}
