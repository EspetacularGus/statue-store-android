package com.statuestore.yours.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class Usuario {

    @SerializedName("DataInscricao")
    @Expose
    private String dataInscricao;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Senha")
    @Expose
    private String senha;
    @SerializedName("Nome")
    @Expose
    private String nome;
    @SerializedName("Sobrenome")
    @Expose
    private String sobrenome;
    @SerializedName("Sexo")
    @Expose
    private String sexo;
    @SerializedName("Cpf")
    @Expose
    private String cpf;
    @SerializedName("DataNascimento")
    @Expose
    private String dataNascimento;

    public String getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(String dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}