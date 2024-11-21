/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author Aluno
 */
public interface IMunicipios {
    public Double calcularDensidadeDemografica(Double populacao, Double area);
    public Double calcularPIBPerCapita(Double populacao, Double pib);
    public String classificarIDHGeral(Double geral);
    public String classificarIDHEducacao(Double educacao);
    public String classificarIDHLongevidade(Double longevidade);
}
