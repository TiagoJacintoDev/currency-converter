import java.io.IOException;
import java.math.BigDecimal;
import java.net.*;
import java.util.Scanner;

public class Main {
    private static void restartApplication(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Conversion error, restarting application...");
        main(args);
    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        var input = new Scanner(System.in);

        System.out.print("Input currency (USD, EUR): ");
        var inputCurrency = input.nextLine();

        System.out.print("Input currency Amount: ");
        var inputCurrencyAmount = input.nextBigDecimal();
        input.nextLine();

        System.out.print("Output currency (USD, EUR): ");
        var outputCurrency = input.nextLine();
        while (inputCurrency.toLowerCase().equals(outputCurrency.toLowerCase())){
            System.out.print("Input currency can't be the same as the output currency, try again: ");
            outputCurrency = input.nextLine();
        }

        ConversionResponse conversion = HttpActions.getConversion(inputCurrency, inputCurrencyAmount, outputCurrency);

        if(conversion == null){
            restartApplication(args);
        }

        BigDecimal outputCurrencyAmount = inputCurrencyAmount.multiply(conversion.exchangeRate);

        System.out.println(
                inputCurrencyAmount + " " + inputCurrency
                + " = " + outputCurrencyAmount + " " + outputCurrency
                + " with exchange rate " + conversion.exchangeRate);
    }
}