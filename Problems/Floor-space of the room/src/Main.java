import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        switch (type) {
            case "triangle": {
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double c = scanner.nextDouble();
                double p = (a + b + c) / 2;
                double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                System.out.println(s);
                break;
            }
            case "rectangle": {
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double s = a * b;
                System.out.println(s);
                break;
            }
            case "circle": {
                double r = scanner.nextDouble();
                double s = 3.14 * r * r;
                System.out.println(s);
                break;
            }
        }
    }
}