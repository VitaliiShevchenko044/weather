module com.weather.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jsobject;
    requires org.json;


    opens com.weather to javafx.fxml;
    exports com.weather;
}