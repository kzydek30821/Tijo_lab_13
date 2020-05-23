package pl.edu.pwsztar;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Podczas implementacji prosze pamietac o zasadzie F.I.R.S.T
public class ShoppingCartTest {
    private ShoppingCartOperation shoppingCart = new ShoppingCart();


    @ParameterizedTest
    @CsvSource({
            "vifon,  24,  3",
            "mango,   2,  7",
            "papaja,  2,  9",
            "baton czekoladowy,  30,  5"
    })
    void addCorrectProduct(String productName, int price, int quantity) {
        assertTrue(shoppingCart.addProducts(productName,price,quantity));
    }

    @ParameterizedTest
    @CsvSource({
            "vifon, -24, 3",
            "mango, -2, 7",
            "papaja, -2, 9",
            "baton czekoladowy, -30, 5"
    })
    void addIncorrectProduct(String productName, int price, int quantity) {
        assertFalse(shoppingCart.addProducts(productName,price,quantity));
    }

    @ParameterizedTest
    @CsvSource({
            "vifon, 24, 3, 2",
            "mango, 2, 7, 2",
            "papaja, 2, 9, 1",
            "baton czekoladowy,  30, 5, 6"
    })
    public void shouldDeleteProduct(String productName, int price, int quantity, int quantityDelete) {
        shoppingCart.addProducts(productName,price,quantity);
        boolean isDeleted = shoppingCart.deleteProducts(productName,quantityDelete);
        assertTrue(isDeleted);
    }



    @Test
    public void shouldGetCorrectQuantity() {
        shoppingCart.addProducts("mango", 2,7);
        shoppingCart.addProducts("papaja",5,2);
        shoppingCart.addProducts("baton czekoladowy",29,3);
        int numberOfMango = shoppingCart.getQuantityOfProduct("mango");
        int numberOfPapaja = shoppingCart.getQuantityOfProduct("papaja");
        int numberOfBar = shoppingCart.getQuantityOfProduct("baton czekoladowy");
        assertEquals(7, numberOfMango);
        assertEquals(22, numberOfPapaja);
        assertEquals(3, numberOfBar);
    }
    @Test
    public void shouldGetCorrectPriceOfAllItems() {
        shoppingCart.addProducts("mango", 2,7);
        shoppingCart.addProducts("papaja",5,2);
        shoppingCart.addProducts("baton czekoladowy",29,3);
        int sumPricesOfAllProducts = shoppingCart.getSumProductsPrices();
        assertEquals(111, sumPricesOfAllProducts);
    }

    @ParameterizedTest
    @CsvSource({
            "vifon,  24,  3",
            "mango,   2,  7",
            "papaja,  2,  9",
            "baton czekoladowy,  30,  5"
    })
    void shouldGetCorrectPrice(String productName, int price, int quantity) {
        shoppingCart.addProducts(productName,price,quantity);
        int priceOfProduct = shoppingCart.getProductPrice(productName);
        assertEquals(price, priceOfProduct);
    }
}
