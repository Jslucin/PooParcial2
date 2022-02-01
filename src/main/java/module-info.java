module com.mycompany.proyecto_2p_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto_2p_poo to javafx.fxml;
    exports com.mycompany.proyecto_2p_poo;
}
