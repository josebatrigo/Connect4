module com.aetxabao.connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.aetxabao.connect4 to javafx.fxml;
    exports com.aetxabao.connect4;
}