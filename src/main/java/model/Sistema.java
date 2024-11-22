/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import application.Conexao;
import application.Municipios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class Sistema {
    
    public static String getDateTime(Timestamp dataAtualizacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(dataAtualizacao);
    }
    
    public void importarArquivoCSV(String path){
        Conexao.novaTabela();
        Municipios municipio = null;
        System.out.println("Entrou no importaArquivoCSV na class Sistema");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            String line = br.readLine();
            
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                int codigo = Integer.parseInt(data[0]);
                double populacao = Double.parseDouble(data[6].replace(".", "").replace(",", "."));
                double domicilios = Double.parseDouble(data[7].replace(".", "").replace(",", "."));
                String municpio = data[1];
                //listMunicipios.add(municpio);
                String microrregiao = data[2];
                String estado = data[3];
                String regiaoGeografica = data[4];
                double area = Double.parseDouble(data[5].replace(".", "").replace(",", "."));
                double pibTotal = Double.parseDouble(data[8].replace(".", "").replace(",", "."));
                double rendaMedia = Double.parseDouble(data[10].replace(".", "").replace(",", "."));
                double rendaNominal = Double.parseDouble(data[11].replace(".", "").replace(",", "."));
                double peaDia = Double.parseDouble(data[12].replace(".", "").replace(",", "."));
                double idhGeral = Double.parseDouble(data[9].replace(".", "").replace(",", "."));
                double idhEducacao = Double.parseDouble(data[13].replace(".", "").replace(",", "."));
                double idhLongevidade = Double.parseDouble(data[14].replace(".", "").replace(",", "."));
                Timestamp dataAtualizacao = new Timestamp(System.currentTimeMillis());
                pibTotal = (pibTotal * 1000);
                municipio = new Municipios(codigo, municpio, microrregiao, estado, regiaoGeografica, area, populacao, domicilios, pibTotal,  rendaMedia, rendaNominal, peaDia, idhGeral, idhEducacao, idhLongevidade, dataAtualizacao);
                Conexao.adicionarUmaNovaLinhaNaTabela("municipios", municipio);
            }
            
        }catch(IOException e){
            System.out.println("Error> " + e.getMessage());
        }
    }
    
    @FXML
    public void exportaArquivoCSV() {
        // Cria um novo FileChooser para o usuário escolher onde salvar o arquivo
        FileChooser fileChooser = new FileChooser();

        // Define o filtro de extensão para salvar apenas arquivos .csv
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        // Define o nome padrão do arquivo
        fileChooser.setInitialFileName("Municipios_Export.csv");

        // Abre o diálogo de "salvar como"
        File file = fileChooser.showSaveDialog(null);

        // Verifica se o usuário selecionou um arquivo (se não, retorna)
        if (file != null) {
            String filePath = file.getAbsolutePath();  // Pega o caminho absoluto do arquivo escolhido
            
            // Chama o método para exportar os dados para o arquivo CSV
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                List<Municipios> listaMunicipios = null;
                listaMunicipios = Conexao.getListaDeMunicipios();
                Locale localeBrasil = new Locale("pt", "BR");

                // Formatar o valor para a moeda BRL
                NumberFormat nf = NumberFormat.getCurrencyInstance(localeBrasil);
                // Formato o número para exibir
                DecimalFormat df = new DecimalFormat("#,##0.##");

                // Cabeçalho do CSV
                String lineMenu = "Código IBGE;Municípios;Microrregião;Estado;Região Geográfica;Área Km²;População;Domicílios;PIB Total (R$ mil);IDH - Índice de Desenv. Humano;Renda Média;Renda Nominal;PEA Dia;IDH - Dimensão Educação;IDH - Dimensão Longevidade; Última Atualização";
                bw.write(lineMenu);
                bw.newLine();

                // Itera sobre a lista de municípios (supondo que "municipios" seja uma lista de objetos Municipio)
                for (Municipios municipio : listaMunicipios) {
                    // Cria as strings com os dados formatados
                    String codigo = String.valueOf(municipio.getCodigo());
                    String nome = municipio.getNomeMunicipio();
                    String regiao = municipio.getRegiao();
                    String estado = municipio.getEstado();
                    String micro = municipio.getMicrorregiao();
                    String area = df.format(municipio.getAreaKmQuadrado());
                    String populacao = df.format(municipio.getPopulacao());
                    String domicilios = df.format(municipio.getDomicilios());
                    String pib = nf.format(municipio.getPibTotal() / 1000).replaceAll("[^\\d.,]", "");
                    String pea = nf.format(municipio.getPeaDia()).replaceAll("[^\\d.,]", "");
                    String rendaMedia = nf.format(municipio.getRendaMedia()).replaceAll("[^\\d.,]", "");
                    String rendaNominal = nf.format(municipio.getRendaNominal()).replaceAll("[^\\d.,]", "");
                    String idhGeral = String.valueOf(municipio.getIdhGeral());
                    String idhEducacao = String.valueOf(municipio.getIdhEducacao());
                    String idhLongevidade = String.valueOf(municipio.getIdhLongevidade());
                    //String DateUltimaAtualizacao = String.valueOf(municipio.getDateUltimaModificacao());

                    // Cria a linha de dados para o CSV
                    String line = codigo + ";" + nome + ";" + regiao + ";" + estado + ";" + micro + ";" + area + ";" + populacao + ";" 
                                + domicilios + ";" + pib + ";" + idhGeral + ";" + rendaMedia + ";" + rendaNominal + ";" + pea + ";" 
                                + idhEducacao + ";" + idhLongevidade;

                    // Escreve a linha no arquivo CSV
                    bw.write(line);
                    bw.newLine();
                }

                // Mensagem de sucesso
                System.out.println("Arquivo CSV exportado com sucesso para: " + filePath);

            } catch (IOException e) {
                System.out.println("Erro ao exportar arquivo CSV: " + e.getMessage());
            }
        } else {
            // Caso o usuário tenha cancelado a seleção de arquivo
            System.out.println("Nenhum arquivo foi selecionado.");
        }
    }

    @FXML
    public void importaCSV() {
        // Cria um novo Stage para o FileChooser
        Stage stage = new Stage(); // Novo Stage temporário

        // Cria um FileChooser para o usuário selecionar o arquivo
        FileChooser fc = new FileChooser();

        // Define o filtro para selecionar apenas arquivos .csv
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        // Abre a janela de seleção de arquivo no novo Stage temporário
        File file = fc.showOpenDialog(stage);  // Passa o novo Stage para o FileChooser

        // Verifica se o arquivo foi selecionado
        if (file != null) {
            // Pega o caminho absoluto do arquivo
            String filePath = file.getAbsolutePath();

            // Exibe o caminho do arquivo no console
            System.out.println("Arquivo selecionado: " + filePath);

            importarArquivoCSV(filePath);
        } else {
            // Caso o arquivo não tenha sido selecionado
            System.out.println("Nenhum arquivo foi selecionado.");
        }
    }
}
