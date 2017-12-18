package com.labs.android.lab7;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public final class Product implements Parcelable, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int upc;
    private String manufacturer;
    private double price;
    private String expiration;
    private int amount;

    public Product(int id, String name, int upc, String manufacturer, double price, String expiration, int amount) {
        this.id = id;
        this.name = name;
        this.upc = upc;
        this.manufacturer = manufacturer;
        this.price = price;
        this.expiration = expiration;
        this.amount = amount;
    }

    public Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        upc = in.readInt();
        manufacturer = in.readString();
        price = in.readDouble();
        expiration = in.readString();
        amount = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + upc + " " + manufacturer + " " + price + " " + expiration + " " + amount;
        /*return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", upc=" + upc +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", expiration=" + expiration +
                ", amount=" + amount +
                '}';
                */
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(upc);
        dest.writeString(manufacturer);
        dest.writeDouble(price);
        dest.writeString(expiration);
        dest.writeInt(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getId() != product.getId()) return false;
        if (getUpc() != product.getUpc()) return false;
        if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
        if (getAmount() != product.getAmount()) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null)
            return false;
        if (getManufacturer() != null ? !getManufacturer().equals(product.getManufacturer()) : product.getManufacturer() != null)
            return false;
        return getExpiration() != null ? getExpiration().equals(product.getExpiration()) : product.getExpiration() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getUpc();
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getExpiration() != null ? getExpiration().hashCode() : 0);
        result = 31 * result + getAmount();
        return result;
    }
}
