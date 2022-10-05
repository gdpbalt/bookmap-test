package org.example.model;

public class Shares {
    private int size;
    private SharesType type;

    public Shares(int size, SharesType type) {
        this.size = size;
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public SharesType getType() {
        return type;
    }

    public void setType(SharesType type) {
        this.type = type;
    }
}
