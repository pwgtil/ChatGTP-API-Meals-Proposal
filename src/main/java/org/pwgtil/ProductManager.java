package org.pwgtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ProductManager {
    private final Path path;

    public ProductManager() throws URISyntaxException {
        ClassLoader classLoader = ProductManager.class.getClassLoader();
        path = Paths.get(Objects.requireNonNull(classLoader.getResource("products.txt")).toURI());
    }

    public List<String> getProducts() throws IOException {
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public void addProduct(String product) throws IOException {
        HashSet<String> productsList = new HashSet<>(getProducts());
        if (!productsList.contains(product)) {
            Files.writeString(path, System.lineSeparator() + product, StandardOpenOption.APPEND);
        } else {
            System.out.println("Product already on the list!");
        }
    }

    public void removeProduct(String product) throws IOException {
        HashSet<String> productList = new HashSet<>(getProducts());
        if (productList.contains(product)) {
            productList.remove(product);
            var productsToSave = String.join(System.lineSeparator(), productList);
            Files.writeString(path, productsToSave);
        } else {
            System.out.println("Product not on the list!");
        }
    }
}
