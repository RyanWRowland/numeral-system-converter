package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = 0;
        String sourceNumber = "";
        int targetRadix = 0;

        try {
            sourceRadix = Integer.parseInt(scanner.nextLine());
            sourceNumber = scanner.nextLine();
            targetRadix = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }

        System.out.println(NumeralSystemConverter.convert(sourceRadix, sourceNumber, targetRadix));

    }
}
