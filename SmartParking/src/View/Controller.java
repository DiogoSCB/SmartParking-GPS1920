package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

public class Controller
{
    // Buttons
    @FXML
    private Button aceitarBtn;
    @FXML
    private Button rejeitarBtn;
    @FXML
    private Button gravarBtn;
    @FXML
    private Button sairBtn;

    // Tabs
    @FXML
    private Tab condutoresTab;
    @FXML
    private Tab pedidosTab;
    @FXML
    private Tab estatisticasTab;

    // Tables
    @FXML
    private TableView condutoresTable;




    // Callbacks

    // Buttons
    @FXML
    public void aceitarBtnClicked()
    {
        System.out.println("Aceitar Button clicked.");


    }
    @FXML
    public void rejeitarBtnClicked()
    {
        System.out.println("Rejeitar Button clicked.");


    }
    @FXML
    public void gravarBtnClicked()
    {
        System.out.println("Gravar Button clicked.");


    }
    @FXML
    public void sairBtnClicked()
    {
        System.out.println("Sair Button clicked.");


    }

    // Tabs
    @FXML
    public void condutoresTabSelected()
    {
        System.out.println("Condutores tab selected.");

        // Hide aceitar e rejeitar
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);

        // Show gravar
        gravarBtn.setVisible(true);

    }
    @FXML
    public void pedidosTabSelected()
    {
        System.out.println("Condutores tab selected.");
        // Hide gravar
        gravarBtn.setVisible(false);

        // Show aceitar e rejeitar
        aceitarBtn.setVisible(true);
        rejeitarBtn.setVisible(true);
    }
    @FXML
    public void estatisticasTabSelected()
    {
        System.out.println("Condutores tab selected.");
        // Hide aceitar, rejeitar, gravar
        aceitarBtn.setVisible(false);
        rejeitarBtn.setVisible(false);
        gravarBtn.setVisible(false);

        // Show sair
        sairBtn.setVisible(true);
    }
}