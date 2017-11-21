package javafxmvc.controller;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafxmvc.model.dao.ItemDeVendaDAO;
import javafxmvc.model.dao.ProdutoDAO;
import javafxmvc.model.dao.VendaDAO;
import javafxmvc.model.database.Database;
import javafxmvc.model.database.DatabaseFactory;
import javafxmvc.model.domain.Venda;

public class FXMLAnchorPaneProcessosVendasController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableView<Venda> tableViewVendas;
    @FXML
    private TableColumn<Venda, Integer> tableColumnVendasCodigo;
    @FXML
    private TableColumn<Venda, LocalDate> tableColumnVendasData;
    @FXML
    private TableColumn<Venda, Venda> tableColumnVendasCliente;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    
    private List<Venda> listVendas;
    private ObservableList<Venda> observableListVendas;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VendaDAO vendaDAO = new VendaDAO();
    private final ItemDeVendaDAO itemDeVendaDAO = new ItemDeVendaDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    @FXML
    private Label labelVendaCodigo;
    @FXML
    private Label labelVendaData;
    @FXML
    private Label labelVendaValor;
    @FXML
    private Label labelVendaPago;
    @FXML
    private Label labelVendaCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vendaDAO.setConnection(connection);
        
        carregarTableViewVendas();
        selecionarItemTableViewVendas(null);
        
        tableViewVendas.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> selecionarItemTableViewVendas(newValue));
    }
    
    public void selecionarItemTableViewVendas(Venda venda){
        if(venda != null){
            labelVendaCodigo.setText(venda.getCdVenda() + "");
            labelVendaData.setText(venda.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            labelVendaValor.setText(String.format("%.2f", venda.getValor()));
            labelVendaPago.setText(venda.getPago() + "");
            labelVendaCliente.setText(venda.getCliente().toString());
        }else{
            labelVendaCodigo.setText("");
            labelVendaData.setText("");
            labelVendaValor.setText("");
            labelVendaPago.setText("");
            labelVendaCliente.setText("");
        }
    }
    
    public void carregarTableViewVendas(){
        tableColumnVendasCodigo.setCellValueFactory(new PropertyValueFactory<>("cdVenda"));
        tableColumnVendasData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnVendasCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        
        listVendas = vendaDAO.listar();
        
        observableListVendas = FXCollections.observableArrayList(listVendas);
        tableViewVendas.setItems(observableListVendas);
    }
}
