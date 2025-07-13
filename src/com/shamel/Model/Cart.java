package com.shamel.Model;

// the Type of the cart: phycial or virtual (online) cart?

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Type{
    PHYSICAL, VIRTUAL
        }

public class Cart implements Comparable<Cart>{

    private int id;
    private List<Product> products;
    private LocalDate date;
    private Type type;

    private static int id_maker = 1000;


    // quickly instantiate a cart:

    public Cart() {
        this(Type.VIRTUAL);
    }

    public Cart(Type type) {
        this(id_maker++ ,  LocalDate.now(), type);
    }

    public Cart(int id, LocalDate date, Type type) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.products = new ArrayList<>();

    }

    public Cart(int id, LocalDate date, Type type, List<Product> products) {
        this(id, date, type);
        this.products.addAll(products);
    }

    public void addItems(Product product){
        products.add(product);
        System.out.println(product+" added to your Cart");
    }

    public void addItems(Product product, int how_many){
        for(int i=1; i<= how_many; i++){
            addItems(product);
            i++;
        }
//        System.out.println(product+" added to your Cart");
    }

    public void removeItem(Product product){
        products.remove(product);
        System.out.println(product+" removed from your Cart");
    }

    public void printSalesSlip(){

        System.out.println("items bought:");

        products.forEach(System.out::println);

    }


    // the Id of the Cart will be its unique identifier:

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return 33*id;
    }

    @Override
    public int compareTo(Cart o) {
        int result = this.date.compareTo(o.date);
        if(result == 0){
            result =((Integer) this.id).compareTo(o.id);
        }
        return result;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDate getDate() {
        return date;
    }
}
