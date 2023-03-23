module com.bitsexcel.fxmlsample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.bitsexcel.fxmlsample to javafx.fxml;
    exports com.bitsexcel.fxmlsample;
}
