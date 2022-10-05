package org.example.model;

public class Cost {
    private int price;
    private int size;

    public Cost(int price, int size) {
        this.price = price;
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }
}
