package com.example.yhisl.fruitworld2.models;

/**
 * Created by yhisl on 17/03/2017.
 */
public class Fruit {

    private String name;
    private String description;
    private int icon;
    private int imageBack;
    private int counter;
    public static final int MAX_CANTIDAD = 10;
    public static final int MIN_CANTIDAD = 0;

    public Fruit(String name, String description, int icon , int imageBack,int counter){
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.imageBack = imageBack;
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImageBack() {
        return imageBack;
    }

    public void setImageBack(int imageBack) {
        this.imageBack = imageBack;
    }

    public void addQuantity(int quantity){
        if(this.counter < MAX_CANTIDAD)
            this.counter+= quantity;
    }

    public void ResetCounter(){
        this.counter = MIN_CANTIDAD;
    }
}
