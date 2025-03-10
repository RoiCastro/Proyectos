module com.tacebook {
    requires javafx.controls;
    requires javafx.fxml;

    // Abrir los paquetes necesarios para JavaFX
    opens com.tacebook.view to javafx.fxml;
    opens com.tacebook.controller to javafx.fxml;

    // Exportar los paquetes que otras clases o módulos pueden usar
    exports com.tacebook.view;
    exports com.tacebook.controller;
    exports com.tacebook.model;  // Si otros módulos o clases necesitan acceder al modelo
    exports com.tacebook.persistencia;  // Si otros módulos o clases necesitan acceder a la persistencia
}

