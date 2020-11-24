package converter;

public class NumeralSystemConverter {
    public static String convert(int sourceBase, String number, int newBase) {
        if (sourceBase < 1 || sourceBase > 36) {
            return "Error: invalid source radix. Must be 1 - 36.";
        }
        if (newBase < 1 || newBase > 36) {
            return "Error: invalid target radix. Must be 1 - 36.";
        }
        if (!number.matches("[0-9A-Za-z]+(.[0-9A-Za-z]+)?")) {
            return "Error: invalid number format.";
        }

        if (number.contains(".")) {
            String[] numbers = number.split("\\.");
            return convert(sourceBase, numbers[0], newBase) + "." + convertFraction(sourceBase, numbers[1], newBase);
        }

        if (sourceBase != 1 && newBase != 1) {
            return Integer.toString(Integer.parseInt(number, sourceBase), newBase);
        }

        if (sourceBase == 1 && newBase == 1) {
            return number;
        }

        if (sourceBase == 1) {
            int decimalNumber = 0;
            for (String s : number.split("")) {
                decimalNumber += Integer.parseInt(s);
            }
            return Integer.toString(decimalNumber, newBase);
        } else {
            int decimalNumber = Integer.parseInt(number, sourceBase);
            return "1".repeat(decimalNumber);
        }
    }

    private static String convertFraction(int sourceBase, String fraction, int newBase) {
        double decimalValue = 0;
        if (sourceBase != 10) {
            char[] digits = fraction.toCharArray();
            int power = 1;
            for (char digit : digits) {
                decimalValue += (Character.digit(digit, sourceBase) / Math.pow(sourceBase, power));
                power++;
            }
        } else {
            decimalValue = Double.parseDouble("0." + fraction);
        }

        if (newBase == 10) {
            return String.valueOf(decimalValue).split("\\.")[1];
        }

        StringBuilder newFraction = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            String[] numParts = Double.toString(decimalValue * (double) newBase).split("\\.");
            int integerPart = Integer.parseInt(numParts[0]);
            newFraction.append(Integer.toString(integerPart, newBase));
            decimalValue = Double.parseDouble("0." + numParts[1]);
        }

        return newFraction.toString();
    }
}
