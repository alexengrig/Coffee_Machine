package machine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {
    private final Scanner scanner;
    private final PrintStream printer;
    private final Map<Integer, Drink> menu;
    private final Ingredients storedIngredients = new Ingredients();
    private int cups;
    private int money;

    {
        menu = new HashMap<>();
        Drink espresso = new Drink();
        espresso.price = 4;
        espresso.ingredients = new Ingredients();
        espresso.ingredients.water = 250;
        espresso.ingredients.beans = 16;
        menu.put(1, espresso);
        Drink latte = new Drink();
        latte.price = 7;
        latte.ingredients = new Ingredients();
        latte.ingredients.water = 350;
        latte.ingredients.milk = 75;
        latte.ingredients.beans = 20;
        menu.put(2, latte);
        Drink cappuccino = new Drink();
        cappuccino.price = 6;
        cappuccino.ingredients = new Ingredients();
        cappuccino.ingredients.water = 200;
        cappuccino.ingredients.milk = 100;
        cappuccino.ingredients.beans = 12;
        menu.put(3, cappuccino);
    }

    {
        storedIngredients.water = 400;
        storedIngredients.milk = 540;
        storedIngredients.beans = 120;
        cups = 9;
        money = 550;
    }

    private CoffeeMachine(InputStream input, OutputStream output) {
        scanner = new Scanner(input);
        printer = new PrintStream(output);
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(System.in, System.out);
        coffeeMachine.use();
    }

    private void use() {
        PrintStream printer = this.printer;
        Scanner scanner = this.scanner;
        String action;
        do {
            remaining();
            printer.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            if ("buy".equals(action)) {
                buy();
            } else if ("fill".equals(action)) {
                fill();
            } else if ("take".equals(action)) {
                take();
            } else if ("remaining".equals(action)) {
                remaining();
            }
        } while (!action.equals("exit"));
    }

    private void buy() {
        PrintStream printer = this.printer;
        printer.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        Scanner scanner = this.scanner;
        String action = scanner.nextLine();
        if ("back".equals(action)) {
            return;
        }
        int type = Integer.parseInt(action);
        Drink drink = menu.get(type);
        if (drink != null) {
            make(drink);
        }
    }

    private void make(Drink drink) {
        Ingredients ingredients = drink.ingredients;
        if (storedIngredients.water < ingredients.water) {
            printer.println("Sorry, not enough water!");
            return;
        }
        if (storedIngredients.milk < ingredients.milk) {
            printer.println("Sorry, not enough milk!");
            return;
        }
        if (storedIngredients.beans < ingredients.beans) {
            printer.println("Sorry, not enough coffee beans!");
            return;
        }
        money += drink.price;
        storedIngredients.water -= ingredients.water;
        storedIngredients.milk -= ingredients.milk;
        storedIngredients.beans -= ingredients.beans;
        cups--;
        printer.println("I have enough resources, making you a coffee!");
    }

    private void fill() {
        printer.println("Write how many ml of water do you want to add:");
        int water = scanner.nextInt();
        storedIngredients.water += water;
        printer.println("Write how many ml of milk do you want to add:");
        int milk = scanner.nextInt();
        storedIngredients.milk += milk;
        printer.println("Write how many grams of coffee beans do you want to add:");
        int beans = scanner.nextInt();
        storedIngredients.beans += beans;
        printer.println("Write how many disposable cups of coffee do you want to add:");
        int cups = scanner.nextInt();
        this.cups += cups;
    }

    private void take() {
        printer.printf("I gave you $%d", money);
        money = 0;
    }

    private void remaining() {
        printer.println("The coffee machine has:");
        printer.printf("%d of water\n", storedIngredients.water);
        printer.printf("%d of milk\n", storedIngredients.milk);
        printer.printf("%d of coffee beans\n", storedIngredients.beans);
        printer.printf("%d of disposable cups\n", cups);
        printer.printf("%d of money\n", money);
    }

    private static class Ingredients {
        int water;
        int milk;
        int beans;
    }

    private static class Drink {
        Ingredients ingredients;
        int price;
    }
}
