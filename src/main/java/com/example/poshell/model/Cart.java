package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        Product product = item.getProduct();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().equals(product)) {
                int amount = items.get(i).getAmount();
                amount += item.getAmount();
                if (amount < 0)
                    return false;
                items.remove(i);
                if (amount > 0)
                    return items.add(new Item(product, amount));
                return true;
            }
        }
        return items.add(item);
    }

    public boolean modifyItem(Item item) {
        Product product = item.getProduct();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().equals(product)) {
                int amount = item.getAmount();
                if (amount < 0)
                    return false;
                items.remove(i);
                if (amount > 0)
                    return items.add(new Item(product, amount));
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
