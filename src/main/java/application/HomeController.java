/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.io.File;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Sistema;
import util.Constraints;

/**
 * FXML Controller class
 *
 * @author Pedro Spindola
 */
public class HomeController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxMunicipios;
    @FXML
    private TextField tfCodigo;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfRegiao;
    @FXML
    private TextField tfEstado;
    @FXML
    private TextField tfMicrorregiao;
    @FXML
    private TextField tfArea;
    @FXML
    private TextField tfPopulacao;
    @FXML
    private TextField tfDomicilios;
    @FXML
    private TextField tfDensidade;
    @FXML
    private TextField tfPib;
    @FXML
    private TextField tfPerCapita;
    @FXML
    private TextField tfPea;
    @FXML
    private TextField tfRendaMedia;
    @FXML
    private TextField tfRendaNominal;
    @FXML
    private TextField tfIDHGeral;
    @FXML
    private TextField tfIHGEducacao;
    @FXML
    private TextField tfIHDLongevidade;
    @FXML
    private TextField inCode; 
    @FXML
    private Label laRankingPopulacao;
    @FXML
    private Label laRankingPib;
    @FXML
    private Label laRankingPerCapita;
    @FXML
    private Label laRankingIDHGeral;
    @FXML
    private Label laRankingIDHEducacao;
    @FXML
    private Label laRankingIDHLongevidade;
    @FXML
    private Label laDateUltimaAtualizacao;
    @FXML
    private ImageView imagemIDHGeral;
    @FXML
    private ImageView imagemIDHEducacao;
    @FXML
    private ImageView imagemIDHLongevidade;
    @FXML
    private ImageView imagemBandeiraMunicipio;
    @FXML
    private ImageView imagemMapsMunicipio;
    @FXML
    private Button sair;
    @FXML
    private Button editar;
    @FXML 
    private Button pesquisar; 
    @FXML
    private Button exportar;
    @FXML 
    private Button sobre; 
    
    URL imagePathMuitoAlto = getClass().getResource("/icon/setaMuitoAlto.png");
    URL imagePathAlto = getClass().getResource("/icon/setaAlto.png");
    URL imagePathMedio = getClass().getResource("/icon/setaMedio.png");
    URL imagePathBaixo = getClass().getResource("/icon/setaBaixo.png");
    URL imagePathNull = getClass().getResource("/icon/null.png");

    private boolean isButtonPressed = false; 
    
    @FXML
    private void fecharProgramaButton(ActionEvent event) {
        if (isButtonPressed == false) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("EMGO");
            alert.setHeaderText(null);
            alert.setContentText("Deseja sair do programa?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                Platform.exit(); // Fecha o programa
            }
        }
    }
    
    @FXML
    private void btnSobre(ActionEvent event) {
        // Btn Sobre / Cancelar
        if (isButtonPressed == false) {
            try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLSobre.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("EMGO");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("EMGO");
            alert.setHeaderText(null);
            alert.setContentText("Deseja cancelar as alterações?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String estilo = "-fx-background-color: #F9FFF6; -fx-background-insets: 0; -fx-background-radius: 6; -fx-border-color: transparent;";
                String btnEstilo = "-fx-background-color: #f9fff6; -fx-background-radius: 10; -fx-border-color: #f9fff6; -fx-border-radius: 10;";

                Constraints.setRemoveTextFieldDouble(tfPopulacao);
                Constraints.setRemoveTextFieldDouble(tfDomicilios);
                Constraints.setRemoveTextFieldDouble(tfPib);
                Constraints.setRemoveTextFieldDouble(tfRendaMedia);
                Constraints.setRemoveTextFieldDouble(tfRendaNominal);
                Constraints.setRemoveTextFieldDouble(tfPea);
                Constraints.setRemoveTextFieldDouble(tfIDHGeral);
                Constraints.setRemoveTextFieldDouble(tfIHGEducacao);
                Constraints.setRemoveTextFieldDouble(tfIHDLongevidade);

                tfPopulacao.setStyle(estilo);
                tfDomicilios.setStyle(estilo);
                tfPib.setStyle(estilo);
                tfRendaMedia.setStyle(estilo);
                tfRendaNominal.setStyle(estilo);
                tfPea.setStyle(estilo);
                tfIDHGeral.setStyle(estilo);
                tfIHGEducacao.setStyle(estilo);
                tfIHDLongevidade.setStyle(estilo);

                tfPopulacao.setEditable(false);
                tfDomicilios.setEditable(false);
                tfPib.setEditable(false);
                tfRendaMedia.setEditable(false);
                tfRendaNominal.setEditable(false);
                tfPea.setEditable(false);
                tfIDHGeral.setEditable(false);
                tfIHGEducacao.setEditable(false);
                tfIHDLongevidade.setEditable(false);

                preencherDados(comboBoxMunicipios.getValue());

                editar.setText("Editar");
                exportar.setText("Exportar");
                exportar.setStyle(btnEstilo);
                exportar.setTextFill(Color.web("#e8bb00"));
                sobre.setText("Sobre");
                isButtonPressed = false;
            } else {
                // VOLTAR
            }
        }
    }
    
    @FXML
    private void ativarButtonEditar() {
        if (!isButtonPressed && comboBoxMunicipios.getSelectionModel().getSelectedItem() != null) {
            String estilo = "-fx-background-color: #F9FFF6; -fx-background-insets: 0; -fx-background-radius: 6; -fx-border-color: #e8bb00;";
            String btnEstiloDelete = "-fx-background-color: #c12838; -fx-background-radius: 10; -fx-border-color: #c12838; -fx-border-radius: 10;";

            tfPopulacao.setText(tfPopulacao.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfDomicilios.setText(tfDomicilios.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfPib.setText(tfPib.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfRendaMedia.setText(tfRendaMedia.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfRendaNominal.setText(tfRendaNominal.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfPea.setText(tfPea.getText().replaceAll("[^\\d,]", "").replace(",", "."));
            tfIDHGeral.setText(tfIDHGeral.getText().replaceAll("[^\\d.]", ""));
            tfIHGEducacao.setText(tfIHGEducacao.getText().replaceAll("[^\\d.]", ""));
            tfIHDLongevidade.setText(tfIHDLongevidade.getText().replaceAll("[^\\d.]", ""));

            Constraints.validacaoParaNumeros(tfPopulacao);
            Constraints.validacaoParaNumerosDecimal(tfDomicilios);
            Constraints.validacaoParaNumerosDecimal(tfPib);
            Constraints.validacaoParaNumerosDecimal(tfRendaMedia);
            Constraints.validacaoParaNumerosDecimal(tfRendaNominal);
            Constraints.validacaoParaNumeros(tfPea);
            Constraints.validacaoParaNumerosDecimal(tfIDHGeral);
            Constraints.validacaoParaNumerosDecimal(tfIHGEducacao);
            Constraints.validacaoParaNumerosDecimal(tfIHDLongevidade);
            Constraints.validacaoParaNumerosDecimalComLimiteDeDigito(tfIDHGeral);
            Constraints.validacaoParaNumerosDecimalComLimiteDeDigito(tfIHGEducacao);
            Constraints.validacaoParaNumerosDecimalComLimiteDeDigito(tfIHDLongevidade);

            tfPopulacao.setStyle(estilo);
            tfDomicilios.setStyle(estilo);
            tfPib.setStyle(estilo);
            tfRendaMedia.setStyle(estilo);
            tfRendaNominal.setStyle(estilo);
            tfPea.setStyle(estilo);
            tfIDHGeral.setStyle(estilo);
            tfIHGEducacao.setStyle(estilo);
            tfIHDLongevidade.setStyle(estilo);

            tfPopulacao.setEditable(true);
            tfDomicilios.setEditable(true);
            tfPib.setEditable(true);
            tfRendaMedia.setEditable(true);
            tfRendaNominal.setEditable(true);
            tfPea.setEditable(true);
            tfIDHGeral.setEditable(true);
            tfIHGEducacao.setEditable(true);
            tfIHDLongevidade.setEditable(true);

            editar.setText("Salvar");
            exportar.setText("Limpar");
            exportar.setStyle(btnEstiloDelete);
            exportar.setTextFill(Color.web("#f9fff6"));
            sobre.setText("Cancelar");
            isButtonPressed = true;
        } else if (isButtonPressed) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("EMGO");
            alert.setHeaderText(null);
            alert.setContentText("Deseja salvar as alterações?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //atualizarDados(comboBoxMunicipios.getValue());
            } else {
                // NÃO SALVO
            }
            String estilo = "-fx-background-color: #F9FFF6; -fx-background-insets: 0; -fx-background-radius: 6; -fx-border-color: transparent;";
            String btnEstilo = "-fx-background-color: #f9fff6; -fx-background-radius: 10; -fx-border-color: #f9fff6; -fx-border-radius: 10;";

            Constraints.setRemoveTextFieldDouble(tfPopulacao);
            Constraints.setRemoveTextFieldDouble(tfDomicilios);
            Constraints.setRemoveTextFieldDouble(tfPib);
            Constraints.setRemoveTextFieldDouble(tfRendaMedia);
            Constraints.setRemoveTextFieldDouble(tfRendaNominal);
            Constraints.setRemoveTextFieldDouble(tfPea);
            Constraints.setRemoveTextFieldDouble(tfIDHGeral);
            Constraints.setRemoveTextFieldDouble(tfIHGEducacao);
            Constraints.setRemoveTextFieldDouble(tfIHDLongevidade);

            tfPopulacao.setStyle(estilo);
            tfDomicilios.setStyle(estilo);
            tfPib.setStyle(estilo);
            tfRendaMedia.setStyle(estilo);
            tfRendaNominal.setStyle(estilo);
            tfPea.setStyle(estilo);
            tfIDHGeral.setStyle(estilo);
            tfIHGEducacao.setStyle(estilo);
            tfIHDLongevidade.setStyle(estilo);

            tfPopulacao.setEditable(false);
            tfDomicilios.setEditable(false);
            tfPib.setEditable(false);
            tfRendaMedia.setEditable(false);
            tfRendaNominal.setEditable(false);
            tfPea.setEditable(false);
            tfIDHGeral.setEditable(false);
            tfIHGEducacao.setEditable(false);
            tfIHDLongevidade.setEditable(false);

            preencherDados(comboBoxMunicipios.getValue());

            editar.setText("Editar");
            exportar.setText("Exportar");
            exportar.setStyle(btnEstilo);
            exportar.setTextFill(Color.web("#e8bb00"));
            sobre.setText("Sobre");
            isButtonPressed = false;
        }
    }
    
    public void preencherDados(String municipioSelecionado) {
        Locale localeBrasil = new Locale("pt", "BR");
        Municipios municipio = Conexao.consultarMunicipioPeloNome(municipioSelecionado);
        municipio.calcularRanking();
                            
        // Formatar o valor para a modeda BRL
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeBrasil);
        // Formato o numero para exibir.
        DecimalFormat df = new DecimalFormat("#,##0.##");

        //municipios.atualizarDados();

        tfCodigo.setText(String.valueOf(municipio.getCodigo()));
        tfNome.setText(municipio.getNomeMunicipio());
        tfRegiao.setText(municipio.getRegiao());
        tfEstado.setText(municipio.getEstado());
        tfMicrorregiao.setText(municipio.getMicrorregiao());
        tfArea.setText(String.valueOf(df.format(municipio.getAreaKmQuadrado()) + " km²"));
        tfPopulacao.setText(String.valueOf(df.format(municipio.getPopulacao()) + " hab"));
        tfDomicilios.setText(String.valueOf(df.format(municipio.getDomicilios())));
        tfDensidade.setText(String.valueOf(df.format(municipio.getDensidadeDemografica()) + " hab/km²"));
        tfPib.setText(String.valueOf(nf.format(municipio.getPibTotal())));
        tfPerCapita.setText(String.valueOf(nf.format(municipio.getPibPerCapita())));
        tfPea.setText(String.valueOf(df.format(municipio.getPeaDia())+ " hab"));
        tfRendaMedia.setText(String.valueOf(nf.format(municipio.getRendaMedia())));
        tfRendaNominal.setText(String.valueOf(nf.format(municipio.getRendaNominal())));
        tfIDHGeral.setText(String.valueOf(municipio.getIdhGeral()));
        tfIHGEducacao.setText(String.valueOf(municipio.getIdhEducacao()));
        tfIHDLongevidade.setText(String.valueOf(municipio.getIdhLongevidade()));
        laRankingPopulacao.setText(String.valueOf(municipio.getRankPopulacao() + " º"));
        laRankingPib.setText(String.valueOf(municipio.getRankPIBTotal() + " º"));
        laRankingPerCapita.setText(String.valueOf(municipio.getRankPIBPerCapita() + " º"));
        laRankingIDHGeral.setText(String.valueOf(municipio.getRankIDHGeral() + " º"));
        laRankingIDHEducacao.setText(String.valueOf(municipio.getRankIDHEducacao()+ " º"));
        laRankingIDHLongevidade.setText(String.valueOf(municipio.getRankIDHLongevidade()+ " º"));
        //laDateUltimaAtualizacao.setText(String.valueOf(municipio.getDateUltimaModificacao()));
        
        URL imagePathBandeira = getClass().getResource("/bandeiras/" + Integer.toString(municipio.getCodigo()) + "_ban.png");
        URL imagePathMapas = getClass().getResource("/maps/" + Integer.toString(municipio.getCodigo()) + "_map.png");

        imagemBandeiraMunicipio.setImage(new Image(imagePathBandeira.toString()));
        imagemMapsMunicipio.setImage(new Image(imagePathMapas.toString()));
        
        // Atualiza a imagem para o IDH Geral
        if ("Muito Alto".equals(municipio.getClassIDHGeral())) {
            imagemIDHGeral.setImage(new Image(imagePathMuitoAlto.toString()));
        } else if ("Alto".equals(municipio.getClassIDHGeral())) {
            imagemIDHGeral.setImage(new Image(imagePathAlto.toString()));
        } else if ("Médio".equals(municipio.getClassIDHGeral())) {
            imagemIDHGeral.setImage(new Image(imagePathMedio.toString()));
        } else if ("Baixo".equals(municipio.getClassIDHGeral())) {
            imagemIDHGeral.setImage(new Image(imagePathBaixo.toString()));
        } else {
            imagemIDHGeral.setImage(new Image(imagePathNull.toString()));
        }

        // Atualiza a imagem para o IDH Educação
        if ("Muito Alto".equals(municipio.getClassIDHEducacao())) {
            imagemIDHEducacao.setImage(new Image(imagePathMuitoAlto.toString()));
        } else if ("Alto".equals(municipio.getClassIDHEducacao())) {
            imagemIDHEducacao.setImage(new Image(imagePathAlto.toString()));
        } else if ("Médio".equals(municipio.getClassIDHEducacao())) {
            imagemIDHEducacao.setImage(new Image(imagePathMedio.toString()));
        } else if ("Baixo".equals(municipio.getClassIDHEducacao())) {
            imagemIDHEducacao.setImage(new Image(imagePathBaixo.toString()));
        } else {
            imagemIDHEducacao.setImage(new Image(imagePathNull.toString()));
        }

        // Atualiza a imagem para o IDH Longevidade
        if ("Muito Alto".equals(municipio.getClassIDHLongevidade())) {
            imagemIDHLongevidade.setImage(new Image(imagePathMuitoAlto.toString()));
        } else if ("Alto".equals(municipio.getClassIDHLongevidade())) {
            imagemIDHLongevidade.setImage(new Image(imagePathAlto.toString()));
        } else if ("Médio".equals(municipio.getClassIDHLongevidade())) {
            imagemIDHLongevidade.setImage(new Image(imagePathMedio.toString()));
        } else if ("Baixo".equals(municipio.getClassIDHLongevidade())) {
            imagemIDHLongevidade.setImage(new Image(imagePathBaixo.toString()));
        } else {
            imagemIDHLongevidade.setImage(new Image(imagePathNull.toString()));
        }
    }
    
    @FXML
    public void importaCSV() {
        Sistema sistema = new Sistema();
        sistema.importaCSV();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Obter a lista de municípios do banco de dados
        ObservableList<String> listMunicipios = Conexao.lerNomesDoMunicipios();
        comboBoxMunicipios.setItems(listMunicipios);
        comboBoxMunicipios.getSelectionModel().clearSelection();
        
        // Método para retorna o valor escolhido pelo o ComboBox
        comboBoxMunicipios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                preencherDados(newValue);
            }
        });
        Constraints.validacaoParaNumeros(inCode);
        /*
        

        // Definir a lista de municípios no ComboBox
        
        
        // Definir por padrão o comboBox para selecionar a primeira linha.
        comboBoxMunicipios.getSelectionModel().clearSelection();
        
        // Método para retorna o valor escolhido pelo o ComboBox
        comboBoxMunicipios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                preencherDados(newValue);
            }
        });
        Constraints.validacaoParaNumeros(inCode);
        */
    }   
    
}
