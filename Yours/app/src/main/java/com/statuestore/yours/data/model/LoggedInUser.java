package com.statuestore.yours.data.model;

public class LoggedInUser {

    private static int id;
    private static String nome;
    private static String email;

    public LoggedInUser(int id, String nome, String email) {
        LoggedInUser.id = id;
        LoggedInUser.nome = nome;
        LoggedInUser.email = email;
    }

    public static int getId() {
        return id;
    }

    public static String getNome() {
        return nome;
    }

    public static String getEmail() {
        return email;
    }
}
