package org.pwgtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        ChatGPTManager chatGPTManager = new ChatGPTManager();

        while (true) {
            System.out.println("\nSelection manu:");
            System.out.println("1. Show my products");
            System.out.println("2. Add product");
            System.out.println("3. Remove product");
            System.out.println("4. Propose three ideas for breakfast");
            System.out.println("5. Propose three ideas for dinner");
            System.out.println("6. Suggest fit & healthy products I can buy");
            System.out.println("7. Exit");

            System.out.println();
            System.out.println("Select what you want to do: ");

            int choice = Integer.parseInt(sc.nextLine());

            System.out.println();

            switch (choice) {
                case 1 -> {
                    System.out.println("Products:");
                    productManager.getProducts().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Write name of the products you want to add:");
                    productManager.addProduct(sc.nextLine());
                }
                case 3 -> {
                    System.out.println("Write name of the products you want to remove");
                    productManager.removeProduct(sc.nextLine());
                }
                case 4 -> {
                    System.out.println("Breakfast ideas:");
                    System.out.println(chatGPTManager.getBreakfastProposal(productManager.getProducts()));
                }
                case 5 -> {
                    System.out.println("Dinner ideas:");
                    System.out.println(chatGPTManager.getDinnerProposal(productManager.getProducts()));
                }
                case 6 -> {
                    System.out.println("Products proposed by ChatGPT:");
                    System.out.println(chatGPTManager.getHealthyProductsRecommendation(productManager.getProducts()));
                }
                case 7 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> {
                }
            }
        }

    }
}