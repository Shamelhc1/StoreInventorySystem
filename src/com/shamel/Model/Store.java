package com.shamel.Model;

import java.util.*;

public class Store {

    private Set<InventoryItem> invetories;
    private Set<Cart> carts;
    private EnumMap<Category, Set<InventoryItem>> aisleInventory = new EnumMap<>(Category.class);

    public Store(Set<InventoryItem> inventories, Set<Cart> carts) {
        this.invetories = inventories;
        this.carts = carts;
        invetories.forEach(
                inventoryItem ->
                aisleInventory.compute(
                        inventoryItem.getProduct().category(),
                        (k,v)-> {

                            Set<InventoryItem> inventorySet;
                            inventorySet=
                                    (v==null) ? new HashSet<>() : v;
                            inventorySet.add(inventoryItem);
                            return inventorySet;
                        })

                );
    }


    //Checks out all the current carts:
    public void checkOutCarts(){

        Map<Product, InventoryItem> mapper = new HashMap<>();
        invetories.forEach(
                i ->
                        mapper.put(i.getProduct(),i)
        );


        carts.forEach(
                cart-> {
                    for(Product product :  cart.getProducts()){

                        InventoryItem i = mapper.get(product);
                        i.reserveItem(1);
                        i.SellItem();

                    }
                }
        );
        carts.clear();

    }




}
