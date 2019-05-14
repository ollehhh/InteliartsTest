 /*
  * Copyright (c) Olehs company, Inc.
  * This software is provided by one person!
  */
package connection;

import domain.Currency;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import java.util.regex.Pattern;
 /**
  * It`s class with realization of parser of Json file.
  * @version 1.0 14 May 2019
  * @author Oleh Zarichnyi
  */
public class Parser {
    /*
    This class implements connection to Json file, parse it and create local Json file to more comfortable work with it.
    Also you can see here saving important information from Json file into collection of Currencys.
     */
    public ArrayList<Currency> parseJSON() {
        ArrayList<Currency> currencies = new ArrayList<Currency>();
        Currency currency;
        JSONObject json = null;
        URL url = null;
        JSONParser parser = new JSONParser();
        String inputLine;
        String input = "";

        try {
            url = new URL("http://data.fixer.io/api/latest?access_key=81d42728a9ba947d2a4515b24dac94a4");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {


            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);

                input = inputLine;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            json = (JSONObject) parser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        input = json.get("rates").toString();

        input = input.replace("{", "");
        input = input.replace("}", "");
        input = input.replace("\"", "");
        input = input.replace(":", " ");
        Pattern spl = Pattern.compile(",");
        String[] splitInfo = spl.split(input);

        spl = Pattern.compile("\\s");
        for (int i = 0; i < splitInfo.length; i++) {
            currency = new Currency();
            String[] splitNext = spl.split(splitInfo[i]);
            currency.setName(splitNext[0]);
            currency.setPrice(Double.parseDouble(splitNext[1]));
            currencies.add(currency);

        }


        return currencies;
    }
}
