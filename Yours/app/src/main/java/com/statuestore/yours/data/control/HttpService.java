package com.statuestore.yours.data.control;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, String> {

    private String email;
    private String senha;

    public HttpService(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        try {
            URL url = new URL("http://localhost:61017/api/validaUsuario?email="
                    + this.email + "&senha=" + this.senha);
            urlConnect(url,resposta,"POST");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta.toString();
    }

    private void urlConnect(URL url, StringBuilder resposta, String method) {
        try {
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();

            urlCon.setRequestMethod(method);
            urlCon.setRequestProperty("Content-type", "application/json");
            urlCon.setRequestProperty("Accept", "application/json");
            urlCon.setDoOutput(true);
            urlCon.setConnectTimeout(10000);

            urlCon.connect();

            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                resposta.append(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
