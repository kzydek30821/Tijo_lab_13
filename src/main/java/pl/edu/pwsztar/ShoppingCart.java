package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart implements ShoppingCartOperation {
    private final List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public boolean addProducts(String productName, int price, int quantity) {
        if(price > 0 && quantity > 0 && quantity <= ShoppingCartOperation.PRODUCTS_LIMIT && getQuantity() <= ShoppingCartOperation.PRODUCTS_LIMIT){
            if(productAlreadyAdded(productName)){
                for(Product product: productList)
                {
                    if(product.getProductName().equals(productName) && product.getPrice() == price){
                        product.setQuantity(product.getQuantity() + quantity);
                        return true;
                    }
                }
            }
            else{
                productList.add(new Product(productName, price, quantity));
                return true;
            }
        }
        return false;
    }

    public boolean deleteProducts(String productName, int quantity) {
        if(quantity > 0)
        {
            for(Product product: productList){
                if(product.getProductName().equals(productName) && product.getQuantity() >= quantity){
                    product.setQuantity(product.getQuantity() - quantity);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int getQuantityOfProduct(String productName) {
        return productList.stream().filter(product -> product.getProductName().equals(productName)).mapToInt(Product::getQuantity).sum();
    }

    public int getSumProductsPrices() {
        return productList.stream().mapToInt(product -> product.getPrice() * product.getQuantity()).sum();
    }

    public int getProductPrice(String productName) {
        int price = 0;
        for(Product x : productList){
            price += x.getPrice();
        }
        return price;
    }

    public List<String> getProductsNames() {
        return productList.stream().map(product -> product.getProductName()).collect(Collectors.toList());
    }

    private int getQuantity(){
        return productList.stream().mapToInt(product -> product.getQuantity()).sum();
    }

    private boolean productAlreadyAdded(String name){
        return productList.stream().anyMatch(product -> product.getProductName().equals(name));
    }
}
