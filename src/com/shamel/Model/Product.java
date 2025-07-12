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

class InventoryItem{

    private Product product;

    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;
    private double SalesPrice;


    public InventoryItem(Product product, int qtyTotal, double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        SalesPrice = salesPrice;
        qtyReserved = 0;
    }

    public InventoryItem(Product product, int qtyTotal,double salesPrice, int qtyReorder, int qtyLow) {
        this(product,qtyTotal,salesPrice );
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;

    }

    // quantity reserved: products in the carts but not yet sold:
    public void reserveItem(int howMany){

        qtyReserved = (howMany > qtyReserved) ? qtyReserved : qtyReserved+howMany;
        int aileRemaining = qtyTotal - qtyReserved;

        System.out.println("Items reserved: " + qtyReserved + " Remaining: " + aileRemaining);
    }

    // method to sell the items reserved
    public void SellItem(){

        qtyTotal -= qtyReserved;
        System.out.println("Items sold: "+ qtyReserved + " Items remaining: " + qtyTotal);
        placeInventoryItem();
    }

    // method to unreserve items
    public void releaseItem(){

        qtyReserved = 0;

    }

    private void placeInventoryItem(){
        if(qtyTotal <= qtyLow) qtyTotal+= qtyReorder;

    }

    public Product getProduct() {
        return product;
    }

}
