module paycore.robust {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens paycore.robust to javafx.fxml;
    exports paycore.robust;
}