package com.statuestore.yours.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Produto{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Nome")
    @Expose
    private String nome;
    @SerializedName("Imagem")
    @Expose
    private String imagem;
    @SerializedName("ImgModelo")
    @Expose
    private String imgModelo;
    @SerializedName("Cor")
    @Expose
    private String cor;
    @SerializedName("Sexo")
    @Expose
    private String sexo;
    @SerializedName("Subgrupo")
    @Expose
    private Integer subgrupo;
    @SerializedName("Cliente")
    @Expose
    private Integer cliente;

    public Produto() {
    }

    public Produto(String nome, String imagem, String imgModelo, String cor, String sexo, Integer subgrupo, Integer cliente) {
        super();
        this.nome = nome;
        this.imagem = imagem;
        this.imgModelo = imgModelo;
        this.cor = cor;
        this.sexo = sexo;
        this.subgrupo = subgrupo;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImgModelo() {
        return imgModelo;
    }

    public void setImgModelo(String imgModelo) {
        this.imgModelo = imgModelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSubgrupo() {
        switch (this.subgrupo) {
            case 3:
                return "Camiseta";
            case 4:
                return "Moletom";
        }

        return "Outro";
    }

    public int getIntSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(Integer subgrupo) {
        this.subgrupo = subgrupo;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

}