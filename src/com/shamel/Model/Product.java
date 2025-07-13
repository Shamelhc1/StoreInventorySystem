package com.shamel.Model;

public record Product(int sku, String name, String manufacturer, Category category) {


    public Product(String name, String manufacturer, Category category) {
        this(0, name, manufacturer, category);
    }

    @Override
    public String toString() {
        return "%-10s%-10s%-10s".formatted(name,category, sku);
    }


    // the sku is unique to the product
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return sku() == product.sku();
    }

    @Override
    public int hashCode() {
        return 33*sku();
    }
}

