package com.statuestore.yours.data.control;

import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.data.model.Produto;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class ProdutoController {


    public Produto cadastrarProduto(Produto produto) {
        Produto result;

        Call<Produto> call = new RetrofitConfig().getProductService().cadastraProduto(
                produto.getNome(),
                produto.getImagem(),
                produto.getImgModelo(),
                produto.getCor(),
                produto.getSexo(),
                produto.getIntSubgrupo(),
                produto.getCliente()
        );

        try {
            result = call.execute().body();
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }

    public List<Produto> listaProduto(int idCliente) {
        List<Produto> produtos;

        Call<List<Produto>> call = new RetrofitConfig().getProductService()
                .listaProduto(idCliente);

        try {
            produtos = call.execute().body();
        } catch (IOException e) {
            produtos = null;
            e.printStackTrace();
        }
        return produtos;
    }

    public boolean deleteProduto(int idProduto) {
        Call<Boolean> call = new RetrofitConfig().getProductService().deleteProduto(idProduto);

        boolean result;
        try {
            result = call.execute().body();
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        }

        return result;
    }
}
