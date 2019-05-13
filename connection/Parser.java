package connection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Parser {
    public Map<String, Double> parseJSON() {
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


        return (Map<String, Double>) json.get("rates");
    }
}
