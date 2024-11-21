/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import application.Conexao;
import application.Municipios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */
public class Sistema {

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
                double idhlongevidade = Double.parseDouble(data[14].replace(".", "").replace(",", "."));
                Conexao.adicionarUmaNovaLinhaNaTabela("municipios", codigo, municpio, microrregiao, estado, regiaoGeografica, area, populacao, domicilios, pibTotal, rendaMedia, rendaNominal, peaDia, idhGeral, idhEducacao, idhlongevidade);
            }
            
        }catch(IOException e){
            System.out.println("Error> " + e.getMessage());
        }
    }
    
    public void exportaArquivoCSV(){
        /*
        String path = "C:\\Projeto Integrador";
        try{
            File outDiretorio = new File(path + "/out");
            if (!outDiretorio.exists()) {
                outDiretorio.mkdir();
            }
            
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\out\\01.ProjetoIntegrador_BaseMunicipios_Out.csv"))) {
            Locale localeBrasil = new Locale("pt", "BR");
                            
        // Formatar o valor para a modeda BRL
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeBrasil);
        // Formato o numero para exibir.
        DecimalFormat df = new DecimalFormat("#,##0.##");
            String lineMenu = "Código IBGE;Municípios;Microrregião;Estado;Região Geográfica;Área Km²;População;Domicílios;PIB Total (R$ mil);IDH - Índice de Desenv. Humano;Renda Média;Renda Nominal;PEA Dia;IDH - Dimensão Educação;IDH - Dimensão Longevidade; Última Atualização";
            bw.write(lineMenu);
            bw.newLine();
                for (Municipio municipio : municipios) {
                    String codigo = (String.valueOf(municipio.getCodigo()));
                    String nome = (municipio.getMunicipio());
                    String regiao = (municipio.getRegiaoGeografica());
                    String estado = (municipio.getEstado());
                    String micro = (municipio.getMicrorregiao());
                    String area = (String.valueOf(df.format(municipio.getArea())));
                    String populacao = (String.valueOf(df.format(municipio.getPopulacao())));
                    String domicilios = (String.valueOf(df.format(municipio.getDomicilios())));
                    String pib = (String.valueOf(nf.format(municipio.getPibTotal() / 1000).replaceAll("[^\\d.,]", "")));
                    String pea = (String.valueOf(nf.format(municipio.getPeaDia()).replaceAll("[^\\d.,]", "")));
                    String rendaMedia = (String.valueOf(nf.format(municipio.getRendaMedia()).replaceAll("[^\\d.,]", "")));
                    String rendaNominal = (String.valueOf(nf.format(municipio.getRendaNominal()).replaceAll("[^\\d.,]", "")));
                    String idhGeral = (String.valueOf(municipio.getIdhGeral()));
                    String idhEducacao = (String.valueOf(municipio.getIdhEducacao()));
                    String idhLongevidade = (String.valueOf(municipio.getIdhlongevidade()));
                    String DateUltimaAtualizacao = (String.valueOf(municipio.getDateUltimaModificacao()));
                    
                    String line = codigo + ";" + nome + ";" + regiao + ";" + estado + ";" + micro + ";" + area + ";" + populacao + ";" + domicilios + ";" + pib + ";" + idhGeral + ";" + rendaMedia + ";" + rendaNominal + ";" + pea + ";" + idhEducacao + ";" +idhLongevidade + ";" + DateUltimaAtualizacao;
                    bw.write(line);
                    bw.newLine();
                }
            }
        }catch(IOException e){
            System.out.println("Error> " + e.getMessage());
        */
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
