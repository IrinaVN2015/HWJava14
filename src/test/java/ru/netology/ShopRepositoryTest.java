package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    ShopRepository repo = new ShopRepository();
    Product product = new Product(1, "хлеб", 65);
    Product product1 = new Product(2, "молоко", 80);
    Product product2 = new Product(3, "масло", 120);
    Product product3 = new Product(4, "конфеты", 150);
    Product product4 = new Product(5, "печенье", 55);


    @BeforeEach
    public void setup() {
        repo.add(product);
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
    }

    @Test
    public void shouldRemoveByExistentId() {

        repo.removeById(5);

        Product[] expected = {product, product1, product2, product3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldRemoveByNonexistentId() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(-4);
        });
    }

    @Test
    public void shouldAdd() {
        Product product5 = new Product(6, "огурец", 64);

        repo.add(product5);

        Product[] expected = {product, product1, product2, product3, product4, product5};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldGenerateAlreadyExistsException() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product1);
        });
    }
}
