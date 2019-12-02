package machine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CoffeeMachine {
    private InputStream input = System.in;
    private OutputStream output = System.out;

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.addMoney(550);
        coffeeMachine.addWater(1200);
        coffeeMachine.addMilk(540);
        coffeeMachine.addBeans(120);
        coffeeMachine.addCups(9);
        coffeeMachine.use();
    }

    private void use() {
        PrintStream printer = getPrinter();
        display();
        printer.println("Write action (buy, fill, take):");
        Scanner scanner = getScanner();
        String action = scanner.nextLine();
        if ("buy".equals(action)) {
            buy();
        } else if ("fill".equals(action)) {
            fill();
        } else if ("take".equals(action)) {
            take();
        }

    }

    private void buy() {
        PrintStream printer = getPrinter();
        printer.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        Scanner scanner = getScanner();
        int type = scanner.nextInt();
        if (1 == type) {
            addMoney(4);
            minusWater(250);
            minusBeans(16);
            minusCup();
        } else if (2 == type) {
            addMoney(7);
            minusWater(350);
            minusMilk(75);
            minusBeans(20);
            minusCup();
        } else if (3 == type) {
            addMoney(6);
            minusWater(200);
            minusMilk(100);
            minusBeans(12);
            minusCup();
        }
        display();
    }

    private void fill() {
        PrintStream printer = getPrinter();
        Scanner scanner = getScanner();
        printer.println("Write how many ml of water do you want to add:");
        int water = scanner.nextInt();
        addWater(water);
        printer.println("Write how many ml of milk do you want to add:");
        int milk = scanner.nextInt();
        addMilk(milk);
        printer.println("Write how many grams of coffee beans do you want to add:");
        int beans = scanner.nextInt();
        addBeans(beans);
        printer.println("Write how many disposable cups of coffee do you want to add:");
        int cups = scanner.nextInt();
        addCups(cups);
        display();
    }

    private void take() {
        PrintStream printer = getPrinter();
        printer.printf("I gave you $%d", money);
        minusMoney(money);
        display();
    }

    private Scanner getScanner() {
        return new Scanner(input);
    }

    private PrintStream getPrinter() {
        return new PrintStream(output);
    }

    private void display() {
        PrintStream printer = getPrinter();
        printer.println("The coffee machine has:");
        printer.printf("%d of water\n", water);
        printer.printf("%d of milk\n", milk);
        printer.printf("%d of coffee beans\n", beans);
        printer.printf("%d of disposable cups\n", cups);
        printer.printf("%d of money", money);
    }

    private void addWater(int water) {
        this.water += water;
    }

    private void minusWater(int water) {
        this.water -= water;
    }

    private void addMilk(int milk) {
        this.milk += milk;
    }

    private void minusMilk(int milk) {
        this.milk -= milk;
    }

    private void addBeans(int beans) {
        this.beans += beans;
    }

    private void minusBeans(int beans) {
        this.beans -= beans;
    }

    private void addCups(int cups) {
        this.cups += cups;
    }

    private void minusCup() {
        this.cups--;
    }

    private void addMoney(int money) {
        this.money += money;
    }

    private void minusMoney(int money) {
        this.money -= money;
    }
}
