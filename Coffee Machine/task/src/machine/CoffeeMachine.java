package machine;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int beans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        int cups = scanner.nextInt();
        int hasCups = Collections.min(Arrays.asList(hasCupsByWater(water), hasCupsByMilk(milk), hasCupsByBeans(beans)));
        if (water >= needWater(cups) && milk >= needMilk(cups) && beans >= needBeans(cups)) {
            if (hasCups > cups) {
                System.out.printf("Yes, I can make that amount of coffee and even %d more than that", hasCups - cups);
            } else {
                System.out.println("Yes, I can make that amount of coffee");
            }
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", hasCups);
        }
    }

    private static int needWater(int cups) {
        return cups * 200;
    }

    private static int hasCupsByWater(int water) {
        return water / 200;
    }

    private static int needMilk(int cups) {
        return cups * 50;
    }

    private static int hasCupsByMilk(int milk) {
        return milk / 50;
    }

    private static int needBeans(int cups) {
        return cups * 15;
    }

    private static int hasCupsByBeans(int beans) {
        return beans / 15;
    }
}
