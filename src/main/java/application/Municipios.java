/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import interfaces.IMunicipios;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Pedro Spíndola
 */
public class Municipios implements IMunicipios{
    private Integer codigo = null;
    private String nomeMunicipio = null;
    private String microrregiao = null;
    private String estado = null;
    private String regiao = null;
    private Double areaKmQuadrado = null;
    private Double populacao = null;
    private Double domicilios = null;
    private Double pibTotal = null;
    private Double rendaMedia = null;
    private Double rendaNominal = null;
    private Double peaDia = null;
    private Double idhGeral = null;
    private Double idhEducacao = null;
    private Double idhLongevidade = null;
    
    private Double densidadeDemografica = null;
    private Double pibPerCapita = null;
    private String classIDHGeral = null;
    private String classIDHEducacao = null;
    private String classIDHLongevidade = null;
    
    private Integer rankPopulacao = null;
    private Integer rankPIBTotal = null;
    private Integer rankPIBPerCapita = null;
    private Integer rankIDHGeral = null;
    private Integer rankIDHEducacao = null;
    private Integer rankIDHLongevidade = null;
    
    private Timestamp dataAtualizacao = null;

    public Municipios(Integer codigo, String nomeMunicipio, String microrregiao, String estado, String regiao, Double areaKmQuadrado, Double populacao, Double domicilios, Double pibTotal, Double rendaMedia, Double rendaNominal, Double peaDia, Double idhGeral, Double idhEducacao, Double idhLongevidade, Timestamp dataAtualizacao) {
        this.codigo = codigo;
        this.nomeMunicipio = nomeMunicipio;
        this.microrregiao = microrregiao;
        this.estado = estado;
        this.regiao = regiao;
        this.areaKmQuadrado = areaKmQuadrado;
        this.populacao = populacao;
        this.domicilios = domicilios;
        this.rendaMedia = rendaMedia;
        this.pibTotal = pibTotal;
        this.rendaMedia = rendaMedia;
        this.rendaNominal = rendaNominal;
        this.peaDia = peaDia;
        this.idhGeral = idhGeral;
        this.idhEducacao = idhEducacao;
        this.idhLongevidade = idhLongevidade;
        this.densidadeDemografica = calcularDensidadeDemografica(populacao, areaKmQuadrado);
        this.dataAtualizacao = dataAtualizacao;
        pibPerCapita = calcularPIBPerCapita(populacao, (pibTotal * 1000));
        classIDHGeral = classificarIDHGeral(idhGeral);
        classIDHEducacao = classificarIDHEducacao(idhEducacao);
        classIDHLongevidade = classificarIDHLongevidade(idhLongevidade);
        rankPopulacao = 1;
        rankPIBTotal = 1;
        rankPIBPerCapita = 1;
        rankIDHGeral = 1;
        rankIDHEducacao = 1;
        rankIDHLongevidade = 1;
    }
    
    public void atualizarDados(){
        densidadeDemografica = calcularDensidadeDemografica(populacao, areaKmQuadrado);
        pibPerCapita = calcularPIBPerCapita(populacao, pibTotal);
        classIDHGeral = classificarIDHGeral(idhGeral);
        classIDHEducacao = classificarIDHEducacao(idhEducacao);
        classIDHLongevidade = classificarIDHLongevidade(idhLongevidade);
        calcularRanking();
    };
    
    @Override
    public Double calcularDensidadeDemografica(Double populacao, Double area)throws IllegalArgumentException{
        double resultado = populacao / area;
        return resultado;
    }
    
    @Override
    public Double calcularPIBPerCapita(Double populacao, Double pib)throws IllegalArgumentException{
        if(populacao != 0){
            return pib / populacao;
        }else{
            return 0.0; //Proteção contra divisão por zero
        }
    }
    
    @Override
    public String classificarIDHGeral(Double geral)throws IllegalArgumentException{
        String resultado;
        if(geral >= 0.8){
            resultado = "Muito Alto";
        }else if(geral < 0.8 && geral >= 0.7){
            resultado = "Alto";
        }else if(geral < 0.7 && geral >= 0.55){
            resultado = "Médio";
        }else{
            resultado = "Baixo";
        }
        return resultado;
    }
    
    @Override
    public String classificarIDHEducacao(Double educacao)throws IllegalArgumentException{
        String resultado;
        if(educacao >= 0.8){
            resultado = "Muito Alto";
        }else if(educacao < 0.8 && educacao >= 0.7){
            resultado = "Alto";
        }else if(educacao < 0.7 && educacao >= 0.55){
            resultado = "Médio";
        }else{
            resultado = "Baixo";
        }
        return resultado;
    }
    
    @Override
    public String classificarIDHLongevidade(Double longevidade)throws IllegalArgumentException{
        String resultado;
        if(longevidade >= 0.8){
            resultado = "Muito Alto";
        }else if(longevidade < 0.8 && longevidade >= 0.7){
            resultado = "Alto";
        }else if(longevidade < 0.7 && longevidade >= 0.55){
            resultado = "Médio";
        }else{
            resultado = "Baixo";
        }
        return resultado;
    }

    public void calcularRanking(){
        rankPopulacao = 1;
        rankPIBTotal = 1;
        rankPIBPerCapita = 1;
        rankIDHGeral = 1;
        rankIDHEducacao = 1;
        rankIDHLongevidade = 1;
        List<Municipios> listaMunicipios = Conexao.getListaDeMunicipios();
        
        // Percorrendo a lista com for
        for (int i = 0; i < listaMunicipios.size(); i++) {
            if(listaMunicipios.get(i).getPopulacao() > populacao){
                rankPopulacao++;
            }
            if(listaMunicipios.get(i).getPibTotal() > pibTotal){
                rankPIBTotal++;
            }
            if(listaMunicipios.get(i).getPibPerCapita() > pibPerCapita){
                rankPIBPerCapita++;
            }
            if(listaMunicipios.get(i).getIdhGeral() > idhGeral){
                rankIDHGeral++;
            }
            if(listaMunicipios.get(i).getIdhEducacao() > idhEducacao){
                rankIDHEducacao++;
            }
            if(listaMunicipios.get(i).getIdhLongevidade() > idhLongevidade){
                rankIDHLongevidade++;
            }
        }
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getMicrorregiao() {
        return microrregiao;
    }

    public void setMicrorregiao(String microrregiao) {
        this.microrregiao = microrregiao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getAreaKmQuadrado() {
        return areaKmQuadrado;
    }

    public void setAreaKmQuadrado(Double areaKmQuadrado) {
        this.areaKmQuadrado = areaKmQuadrado;
    }

    public Double getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Double populacao) {
        this.populacao = populacao;
    }

    public Double getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(Double domicilios) {
        this.domicilios = domicilios;
    }

    public Double getPibTotal() {
        return pibTotal;
    }

    public void setPibTotal(Double pibTotal) {
        this.pibTotal = pibTotal;
    }

    public Double getRendaMedia() {
        return rendaMedia;
    }

    public void setRendaMedia(Double rendaMedia) {
        this.rendaMedia = rendaMedia;
    }

    public Double getRendaNominal() {
        return rendaNominal;
    }

    public void setRendaNominal(Double rendaNominal) {
        this.rendaNominal = rendaNominal;
    }

    public Double getPeaDia() {
        return peaDia;
    }

    public void setPeaDia(Double peaDia) {
        this.peaDia = peaDia;
    }

    public Double getIdhGeral() {
        return idhGeral;
    }

    public void setIdhGeral(Double idhGeral) {
        this.idhGeral = idhGeral;
    }

    public Double getIdhEducacao() {
        return idhEducacao;
    }

    public void setIdhEducacao(Double idhEducacao) {
        this.idhEducacao = idhEducacao;
    }

    public Double getIdhLongevidade() {
        return idhLongevidade;
    }

    public void setIdhLongevidade(Double idhLongevidade) {
        this.idhLongevidade = idhLongevidade;
    }

    public Double getDensidadeDemografica() {
        return densidadeDemografica;
    }

    public void setDensidadeDemografica(Double densidadeDemografica) {
        this.densidadeDemografica = densidadeDemografica;
    }

    public Double getPibPerCapita() {
        return pibPerCapita;
    }

    public void setPibPerCapita(Double pibPerCapita) {
        this.pibPerCapita = pibPerCapita;
    }

    public String getClassIDHGeral() {
        return classIDHGeral;
    }

    public void setClassIDHGeral(String classIDHGeral) {
        this.classIDHGeral = classIDHGeral;
    }

    public String getClassIDHEducacao() {
        return classIDHEducacao;
    }

    public void setClassIDHEducacao(String classIDHEducacao) {
        this.classIDHEducacao = classIDHEducacao;
    }

    public String getClassIDHLongevidade() {
        return classIDHLongevidade;
    }

    public void setClassIDHLongevidade(String classIDHLongevidade) {
        this.classIDHLongevidade = classIDHLongevidade;
    }

    public Integer getRankPopulacao() {
        return rankPopulacao;
    }

    public void setRankPopulacao(Integer rankPopulacao) {
        this.rankPopulacao = rankPopulacao;
    }

    public Integer getRankPIBTotal() {
        return rankPIBTotal;
    }

    public void setRankPIBTotal(Integer rankPIBTotal) {
        this.rankPIBTotal = rankPIBTotal;
    }

    public Integer getRankPIBPerCapita() {
        return rankPIBPerCapita;
    }

    public void setRankPIBPerCapita(Integer rankPIBPerCapita) {
        this.rankPIBPerCapita = rankPIBPerCapita;
    }

    public Integer getRankIDHGeral() {
        return rankIDHGeral;
    }

    public void setRankIDHGeral(Integer rankIDHGeral) {
        this.rankIDHGeral = rankIDHGeral;
    }

    public Integer getRankIDHEducacao() {
        return rankIDHEducacao;
    }

    public void setRankIDHEducacao(Integer rankIDHEducacao) {
        this.rankIDHEducacao = rankIDHEducacao;
    }

    public Integer getRankIDHLongevidade() {
        return rankIDHLongevidade;
    }

    public void setRankIDHLongevidade(Integer rankIDHLongevidade) {
        this.rankIDHLongevidade = rankIDHLongevidade;
    }

    public Timestamp getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Timestamp dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    
    
}
