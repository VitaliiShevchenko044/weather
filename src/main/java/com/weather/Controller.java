package com.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import netscape.javascript.JSObject;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.isEmpty()) {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=adddc6e874323c75896d5e1ea87bd825&units=metric");

                if (!output.isEmpty()) {
                    JSONObject object = new JSONObject(output);
                    temp_info.setText("TEMPERATURE:  " + object.getJSONObject("main").getDouble("temp") + " ℃");
                    temp_feels.setText("FEELS:  " + object.getJSONObject("main").getDouble("feels_like") + " ℃");
                    temp_max.setText("MAX:  " + object.getJSONObject("main").getDouble("temp_max") + " ℃");
                    temp_min.setText("MIN:  " + object.getJSONObject("main").getDouble("temp_min") + " ℃");
                    pressure.setText("PRESSURE:  " + object.getJSONObject("main").getDouble("pressure") / 10 + " kPa");
                }
            }
        });
    }
    private static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection(); 

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("city not found");
        }
        return content.toString();

    }
}
