package com.example.lb39;

public class TODO {

    private String Title;
    private String Text;

    public TODO(String title, String text){
        this.Title = title;
        this.Text = text;
    }
    public static String toString(TODO todo){
        return todo.getTitle()+","+todo.getText();
    }
    public static TODO fromString(String str){
        String[] parrts = str.split(",");
        return new TODO(parrts[0], parrts[1]);
    }
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


}