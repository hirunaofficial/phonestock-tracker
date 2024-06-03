package dev.hiruna.phone;

public class TBLPhones {
    private int id;
    private String brand;
    private String model;
    private int qty;
    private String color;
    private int ram;
    private int storage;
    private double price;

    // Constructors, getters, and setters

    public TBLPhones(int id, String brand, String model, int qty, String color, int ram, int storage, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.qty = qty;
        this.color = color;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getQty() {
        return qty;
    }

    public String getColor() {
        return color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public double getPrice() {
        return price;
    }
}
