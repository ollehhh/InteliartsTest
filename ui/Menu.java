package ui;

import connection.Connector;
import connection.Parser;
import domain.Currency;
import domain.Purchase;
import connection.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

public class Menu {
    String info = null;
    ArrayList<Purchase> purchases = new ArrayList<Purchase>();
    Request req = new Request();
    Connector connector = new Connector();
    Integer check = 1;

    public void startMenu() {

        BufferedReader br;
        connector.startConnection();

        Integer i = 0;
        while (check > 0) {
            System.out.println();
            System.out.println();
            System.out.println("Hello in my program :)");
            System.out.println("Select one of functions:");
            System.out.println("1.Purchase.");
            System.out.println("2.All.");
            System.out.println("3.Clear.");
            System.out.println("4.Report.");
            System.out.println("5.Exit.");
            System.out.println("Write number of menu:");
            System.out.println();

            br = new BufferedReader(new InputStreamReader(System.in));
            try {
                i = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            switch (i) {
                case 1: {
                    addPurchase();

                    break;
                }
                case 2: {
                    showAll();

                    break;
                }
                case 3: {

                    clearOne();
                    break;
                }
                case 4: {
                    makeReport();
                    break;
                }
                case 5: {


                    check--;
                    break;

                }
                default: {

                    System.out.println("You write something wrong, pls try again");


                }

            }
        }
        System.out.println("Thanks for using this program ;)");
        connector.closeConnection();
    }

    void addPurchase() {
        System.out.println("For adding new purchase write it in format <<2019-04-25 12 USD “Photo Frame”>> ");
        System.out.println("Where 2019-04-25 is date of purchase, 12 is price, USD is currency and “Photo Frame” is the name of the product purchased");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            info = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern spl = Pattern.compile("\\s");
        String[] splitInfo = spl.split(info, 4);

        if (splitInfo.length > 4) {

        } else {
            if (Pattern.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", splitInfo[0])) {
                if (Pattern.matches("^[+-]?([0-9]*[.])?[0-9]+$", splitInfo[1])) {
                    if (Pattern.matches("^[A-Z]{3}$", splitInfo[2])) {

                        Purchase purchase = new Purchase();
                        purchase.setItem(splitInfo[3]);
                        purchase.setCurrency(splitInfo[2]);
                        purchase.setPrice(Double.parseDouble(splitInfo[1]));
                        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        purchase.setLocalTime(LocalDate.parse(splitInfo[0], DATEFORMATTER));

                        connector.update(req.addPurchase(purchase));


                    } else {
                        System.out.println("Currency of purchase is out of range");

                    }
                } else {
                    System.out.println("Price of purchase is out of range");

                }
            } else {
                System.out.println("Year of purchase is out of range");

            }


        }

    }

    void showAll() {
        purchases.clear();
        System.out.println(" There are all purchases:");
        purchases = connector.select("SELECT * FROM `PURCHASE`  ORDER BY DTime ASC");
        System.out.println();
        System.out.println(purchases.get(0).getLocalTime());
        System.out.println(purchases.get(0).getItem() + " "
                + purchases.get(0).getPrice() + " " + purchases.get(0).getCurrency());


        for (int i = 1; i < purchases.size(); i++) {
            if (purchases.get(i).getLocalTime().equals(purchases.get(i - 1).getLocalTime())) {
                System.out.println();
                System.out.println(purchases.get(0).getItem() + " "
                        + purchases.get(0).getPrice() + " " + purchases.get(0).getCurrency());

            } else {
                System.out.println();
                System.out.println(purchases.get(i).getLocalTime());
                System.out.print(purchases.get(i).getItem() + " ");
                System.out.print(purchases.get(i).getPrice() + "  ");
                System.out.print(purchases.get(i).getCurrency());
            }
        }

    }

    void clearOne() {
        System.out.println("For deleting purchase write date in format <<2019-04-25>>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            info = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Pattern.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", info)) {
            connector.update(req.deletePurchase(info));
        }
    }

    void makeReport() {

        System.out.println("For taking report write year and currency in format <<2019 UAH>>");
        purchases.clear();
        Parser parser = new Parser();
        ArrayList<Currency> currencies = parser.parseJSON();
        Pattern spl = Pattern.compile("\\s");

        Double sum = 0.0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            info = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] splitInfo = spl.split(info, 2);
        if (Pattern.matches("^[0-9]{4}$", splitInfo[0])) {
            if (Pattern.matches("^[A-Z]{3}$", splitInfo[1])) {
                purchases = connector.select("SELECT * FROM `PURCHASE`  WHERE `DTime` BETWEEN '"
                        + splitInfo[0] + "-01-01' AND '"
                        + splitInfo[0] + "-12-31' ");

                for (int i = 0; i < purchases.size(); i++) {

                    for (int j = 0; j < currencies.size(); j++) {

                        if (purchases.get(i).getCurrency().equals(currencies.get(j).getName()))
                            sum += purchases.get(i).getPrice() / currencies.get(j).getPrice();
                    }

                }
                for (int j = 0; j < currencies.size(); j++) {
                    if (splitInfo[1].equals(currencies.get(j).getName())) {
                        sum = sum * currencies.get(j).getPrice();
                    }
                }


            }

        }

        System.out.println();
        System.out.println(sum + " " + splitInfo[1]);


    }

}
