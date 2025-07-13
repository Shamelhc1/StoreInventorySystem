package com.shamel.Model;

import java.time.LocalDate;
import java.util.*;

public class Store {

    private Set<InventoryItem> invetories;
    private Set<Cart> carts;
    private EnumMap<Category, Set<InventoryItem>> aisleInventory = new EnumMap<>(Category.class);


    // instantiates a store, the aisleInventory Map is calculated from the inventories Set
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


    // abandons older carts
    public void abandonCart(){
        List<Cart> oldCart = new ArrayList<>(carts);
        oldCart.removeIf(cart ->  cart.getDate().equals(LocalDate.now()));
        carts.removeAll(oldCart);
    }

    public void listProductsByCategory(){

        aisleInventory.forEach((k,v) ->{

            System.out.println(k);
            v.forEach(i -> System.out.println( " "+ i.getProduct() + "Quantity " +i.getQtyTotal() ));

                });

    }



}
