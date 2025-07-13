package com.shamel.Test;

import com.shamel.Model.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        System.out.println( LocalDate.now());

        // Create sample products
        Product milk = new Product(1001, "Milk", "DairyFarm", Category.DAIRY);
        Product bread = new Product(1002, "Bread", "BakeCorp", Category.BAKERY);
        Product apples = new Product(1003, "Apples", "FreshFarms", Category.PRODUCE);
        Product chicken = new Product(1004, "Chicken", "MeatCo", Category.MEAT);
        Product salmon = new Product(1005, "Salmon", "SeafoodInc", Category.SEAFOOD);
        Product Shrimp = new Product(1006, "Shrimp", "SeafoodInc", Category.SEAFOOD);

        // Create inventory items with stock
        InventoryItem milkInventory = new InventoryItem(milk, 5000, 3.99, 20, 100);
        InventoryItem breadInventory = new InventoryItem(bread, 3000, 2.49, 15, 50);
        InventoryItem applesInventory = new InventoryItem(apples, 1000, 1.99, 50, 200);
        InventoryItem chickenInventory = new InventoryItem(chicken, 2500, 8.99, 10, 50);
        InventoryItem salmonInventory = new InventoryItem(salmon, 1500, 12.99, 8, 30);
        InventoryItem shrimpInventory = new InventoryItem(Shrimp, 1500, 6.99, 8, 30);

        Set<InventoryItem> inventories = new HashSet<>(List.of(milkInventory,
                breadInventory, applesInventory,
                chickenInventory, salmonInventory,
                shrimpInventory));

        // Instantiating a Cart
        Cart cart1 = new Cart();
        cart1.addItems(milk,5);
        cart1.addItems(apples, 11);
        cart1.addItems(salmon, 14);


        Set<Cart> carts = new TreeSet<>();
        carts.add(cart1);

        // Instantiating a Store
        Store store = new Store(inventories, carts);

        // listing all available products in the store by category:
        store.listProductsByCategory();

        // checking out the Carts in the store
        store.checkOutCarts();

        System.out.println();
        store.listProductsByCategory();




    }
}
