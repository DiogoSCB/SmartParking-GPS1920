package models;

public interface Constants
{
    // FXML settings
    String FXML_FILE_NAME = "ui.fxml";

    // Window settings
    String WINDOW_NAME = "Smart Parking 0.1";

    int WINDOW_WIDTH = 1024;
    int WINDOW_HEIGHT = 768;

    // Database settings
    String IP = "localhost";
    String PORT = "3306";
    String DB_NAME = "smartparking";
    String USER = "root";
    String PASS = "123456";

    // Misc
    int ACCEPT = 1;
    int PENDING = 0;
    int REFUSE = -1;
}
