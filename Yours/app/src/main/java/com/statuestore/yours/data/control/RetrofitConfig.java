package com.statuestore.yours.data.control;

import com.statuestore.yours.data.model.Produto;
import com.statuestore.yours.data.model.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:61017/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }
    public ProductService getProductService() { return this.retrofit.create(ProductService.class);}

    public interface UserService {
        @FormUrlEncoded
        @POST("validaUsuario")
        Call<Usuario> validaUsuario(@Field("email") String email,
                                    @Field("senha") String senha);

        @FormUrlEncoded
        @POST("cadastraUsuario")
        Call<Usuario> cadastraUsuario(@Field("email") String email,
                                      @Field("nome") String nome,
                                      @Field("sobrenome") String sobrenome,
                                      @Field("senha") String senha,
                                      @Field("CPF") String cpf,
                                      @Field("sexo") String sexo,
                                      @Field("dataNascimento") String dataNascimento);
    }

    public interface ProductService {
        @FormUrlEncoded
        @POST("cadastraProduto")
        Call<Produto> cadastraProduto(@Field("nome") String nome,
                                      @Field("imagem") String imagem,
                                      @Field("imagemModelo") String imagemModelo,
                                      @Field("hexaCor") String hexaCor,
                                      @Field("sexo") String sexo,
                                      @Field("idSubgrupo") int idSubgrupo,
                                      @Field("idCliente") int idCliente);

        @GET("listaProduto")
        Call<List<Produto>> listaProduto(@Query("idCliente") int idCliente);

        @DELETE("deleteProduto")
        Call<Boolean> deleteProduto(@Query("idProdutoPersonalizado") int idProduto);
    }
}

