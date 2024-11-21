/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Pedro Spindola
 * @date 19/11/2024
 * @brief Class Conexao
 */
public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/projetointegrador";
    private static final String USER = "Pedro";
    private static final String PASSWORD = "Pedro99#";

    // Método para criar a conexão com o banco de dados
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Método para criar uma nova tabela.
    public static void novaTabela() {
        try (Connection connection = createConnection(); 
            Statement stmt = connection.createStatement()) {

            // SQL para criar a tabela 'municipios'
            String createTableQuery = "CREATE TABLE IF NOT EXISTS municipios ("
                    + "codigo INTEGER PRIMARY KEY, "
                    + "nome VARCHAR(100) NOT NULL, "
                    + "microrregiao VARCHAR(100) NOT NULL, "
                    + "estado VARCHAR(100) NOT NULL, "
                    + "regiao VARCHAR(100) NOT NULL, "
                    + "areaKmQuadrado DECIMAL(15, 2) NOT NULL, "
                    + "populacao DECIMAL(15, 2) NOT NULL, "
                    + "domicilios DECIMAL(15, 2) NOT NULL, "
                    + "pibTotal DECIMAL(15, 2) NOT NULL, "
                    + "rendaMedia DECIMAL(15, 2) NOT NULL, "
                    + "rendaNominal DECIMAL(15, 2) NOT NULL, "
                    + "peaDia DECIMAL(15, 2) NOT NULL, "
                    + "idhGeral DECIMAL(15, 2) NOT NULL, "
                    + "idhEducacao DECIMAL(15, 2) NOT NULL, "
                    + "idhLongevidade DECIMAL(15, 2) NOT NULL);";

            // Executando o comando SQL para criar a tabela
            stmt.executeUpdate(createTableQuery);

            // Exibe uma mensagem indicando que a tabela foi criada com sucesso ou já existe
            System.out.println("Tabela 'municipios' criada com sucesso ou já existe.");

        } catch (SQLException e) {
            // Em caso de erro, exibe a mensagem de erro
            System.out.println("Erro ao criar a tabela 'municipios': " + e.getMessage());
        }
    }
    
    public static void adicionarUmaNovaLinhaNaTabela(String tabela, Integer codigo, String nome, String microrregiao, String estado, String regiao, Double areaKmQuadrado, 
                                         Double populacao, Double domicilios, Double pibTotal, Double rendaMedia, 
                                         Double rendaNominal, Double peaDia, Double idhGeral, Double idhEducacao, 
                                         Double idhLongevidade){
        // SQL de inserção
        String insertQuery = "INSERT INTO " + tabela + " (codigo, nome, microrregiao, estado, regiao, areaKmQuadrado, populacao, "
                             + "domicilios, pibTotal, rendaMedia, rendaNominal, peaDia, idhGeral, idhEducacao, idhLongevidade) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = createConnection(); 
            PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            
            // Definindo os parâmetros do PreparedStatement
            pstmt.setInt(1, codigo);
            pstmt.setString(2, nome);
            pstmt.setString(3, microrregiao);
            pstmt.setString(4, estado);
            pstmt.setString(5, regiao);
            pstmt.setDouble(6, areaKmQuadrado);
            pstmt.setDouble(7, populacao);
            pstmt.setDouble(8, domicilios);
            pstmt.setDouble(9, pibTotal);
            pstmt.setDouble(10, rendaMedia);
            pstmt.setDouble(11, rendaNominal);
            pstmt.setDouble(12, peaDia);
            pstmt.setDouble(13, idhGeral);
            pstmt.setDouble(14, idhEducacao);
            pstmt.setDouble(15, idhLongevidade);
            
            // Executando a inserção
            pstmt.executeUpdate();
            
            System.out.println("Dados inseridos com sucesso na tabela 'municipios'.");
        
        
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela 'municipios': " + e.getMessage());
        }
    }
    
    // Método para ler os usuários
    public static void lerUsuarios() {
        String query = "SELECT * FROM usuarios";
        try (Connection connection = createConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Percorre os resultados
            while (rs.next()) {
                String nome = rs.getString("nome_usuario");
                String email = rs.getString("email_usuario");
                String senha = rs.getString("senha_usuario");

                // Exibe os dados ou faz qualquer ação com esses dados
                System.out.println("Nome: " + nome + ", Email: " + email + ", Senha: " + senha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a tabela de usuários: " + e.getMessage());
        }
    }
    
    // Método para ler os usuários
    public static ObservableList<String> lerNomesDoMunicipios(){
        ObservableList<String> nomesMunicipios = FXCollections.observableArrayList();  // Cria uma ObservableList para armazenar os nomes
        String query = "SELECT nome FROM municipios";  // Seleciona apenas o nome do município
        
        // Conecta ao banco de dados e executa a consulta
        try (Connection connection = createConnection();  // Método createConnection() já é seu código para conectar ao banco
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Percorre os resultados e adiciona os nomes à lista
            while (rs.next()) {
                String nome = rs.getString("nome");
                nomesMunicipios.add(nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a tabela de municípios: " + e.getMessage());
        }
        
        // Retorna a lista de nomes
        return nomesMunicipios;
    }
    
    // Método para ler os municipios
    public static Municipios consultarMunicipioPeloNome(String nomeMunicipio){
        Municipios municipio = null;
        String query = "SELECT * FROM municipios";  // Seleciona apenas o nome do município
        
        // Conecta ao banco de dados e executa a consulta
        try (Connection connection = createConnection();  // Método createConnection() já é seu código para conectar ao banco
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Percorre os resultados e adiciona os nomes à lista
            while (rs.next()) {
                String nome = rs.getString("nome");
                
                // Supondo que você tenha um campo 'nomeMunicipio' para comparação
                if (nomeMunicipio != null && nomeMunicipio.equals(nome)) {  // Comparação correta usando equals()
                    Integer codigo = rs.getInt("codigo");
                    String microrregiao = rs.getString("microrregiao");
                    String estado = rs.getString("estado");
                    String regiao = rs.getString("regiao");
                    Double areaKmQuadrado = rs.getDouble("areaKmQuadrado");
                    Double populacao = rs.getDouble("populacao");
                    Double domicilios = rs.getDouble("domicilios");
                    Double pibTotal = rs.getDouble("pibTotal");
                    Double rendaMedia = rs.getDouble("rendaMedia");
                    Double rendaNominal = rs.getDouble("rendaNominal");
                    Double peaDia = rs.getDouble("peaDia");
                    Double idhGeral = rs.getDouble("idhGeral");
                    Double idhEducacao = rs.getDouble("idhEducacao");
                    Double idhLongevidade = rs.getDouble("idhLongevidade");

                    // Cria um novo objeto Municipio com os dados da tabela
                    municipio = new Municipios(codigo, nome, microrregiao, estado, regiao, areaKmQuadrado, populacao, domicilios, pibTotal,  rendaMedia, rendaNominal, peaDia, idhGeral, idhEducacao, idhLongevidade);
                    return municipio;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a tabela de municípios: " + e.getMessage());
        }
        return null;
    }
    
    // Método para retonar lista de municipios
    public static List<Municipios> getListaDeMunicipios(){
        String query = "SELECT * FROM municipios";  // Seleciona apenas o nome do município
        List<Municipios> listaMunicipios = new ArrayList<>();
        // Conecta ao banco de dados e executa a consulta
        System.out.println("Aqui");
        try (
            Connection connection = createConnection();  // Método createConnection() já é seu código para conectar ao banco
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Conectado");
            // Percorre os resultados e adiciona os nomes à lista
            while (rs.next()) {
                System.out.println("No loop");
                Integer codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String microrregiao = rs.getString("microrregiao");
                String estado = rs.getString("estado");
                String regiao = rs.getString("regiao");
                Double areaKmQuadrado = rs.getDouble("areaKmQuadrado");
                Double populacao = rs.getDouble("populacao");
                Double domicilios = rs.getDouble("domicilios");
                Double pibTotal = rs.getDouble("pibTotal");
                Double rendaMedia = rs.getDouble("rendaMedia");
                Double rendaNominal = rs.getDouble("rendaNominal");
                Double peaDia = rs.getDouble("peaDia");
                Double idhGeral = rs.getDouble("idhGeral");
                Double idhEducacao = rs.getDouble("idhEducacao");
                Double idhLongevidade = rs.getDouble("idhLongevidade");
                    
                // Cria um novo objeto Municipio com os dados da tabela
                Municipios municipio = new Municipios(codigo, nome, microrregiao, estado, regiao, areaKmQuadrado, populacao, domicilios, pibTotal,  rendaMedia, rendaNominal, peaDia, idhGeral, idhEducacao, idhLongevidade);
                listaMunicipios.add(municipio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a tabela de municípios: " + e.getMessage());
        }
        return listaMunicipios;
    }
    
    // Método para validar o login no banco de dados
    public static boolean validarLogin(String usuarioInput, String senhaInput) {
        String query = "SELECT * FROM usuarios WHERE nome_usuario = ? AND senha_usuario = ?";
        boolean autenticado = false;

        try (Connection connection = createConnection();  // Estabelece conexão com o banco de dados
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Define os parâmetros da consulta (evita SQL Injection)
            stmt.setString(1, usuarioInput);  // Nome de usuário
            stmt.setString(2, senhaInput);  // Senha

            try (ResultSet rs = stmt.executeQuery()) {
                // Verifica se o usuário foi encontrado
                if (rs.next()) {
                    autenticado = true;  // Usuário e senha encontrados
                    // Você pode definir um objeto "usuario" para armazenar os dados do usuário logado, se necessário
                    // Por exemplo:
                    // Usuario user = new Usuario(rs.getString("nome_usuario"), rs.getString("senha_usuario"));
                    // sistema.setUsuario(user);  // Definir o usuário no sistema
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autenticado;  // Retorna verdadeiro se autenticado, falso caso contrário
    }
}
