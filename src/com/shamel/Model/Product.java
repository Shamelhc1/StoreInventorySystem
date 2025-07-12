package com.shamel.Model;


enum Category{
    DAIRY, PRODUCE, MEAT, HOUSEHOLD, SEAFOOD, DELI, BAKERY,
}

public record Product(int sku, String name, String manufacturer, Category category) {

    @Override
    public String toString() {
        return "%-10s%-10s%-10s%-10s".formatted(name,category,manufacturer, sku);
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

