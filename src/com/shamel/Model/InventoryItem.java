package com.shamel.Model;

public class InventoryItem{

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
        int unreserved = qtyTotal - qtyReserved;
        qtyReserved = (howMany > unreserved) ? qtyReserved : qtyReserved+howMany;
        int aileRemaining = qtyTotal - qtyReserved;

//        System.out.println("Items reserved: " + qtyReserved + " Remaining: " + aileRemaining);
    }

    // method to sell the items reserved
    public void SellItem(){

        qtyTotal = qtyTotal - qtyReserved;
        System.out.println( this.product.name()+" Items sold: "+ qtyReserved + " Items remaining: " + qtyTotal);
        placeInventoryItem();

        //Adjusts the reserved items to none after said item is sold.
        releaseItem();
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

    public int getQtyTotal() {
        return qtyTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItem that = (InventoryItem) o;
        return getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return 33* getProduct().hashCode();
    }
}
