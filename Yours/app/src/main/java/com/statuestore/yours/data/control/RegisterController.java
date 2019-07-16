package com.statuestore.yours.data.control;

import com.statuestore.yours.data.model.Usuario;
import retrofit2.Call;

public class RegisterController {

    private Usuario result;

    public Usuario cadastrar(Usuario usuario) {
        String email = usuario.getEmail();
        String nome = usuario.getNome();
        String sobrenome = usuario.getSobrenome();
        String senha = usuario.getSenha();
        String cpf = usuario.getCpf();
        String sexo = usuario.getSexo();
        String dataNasc = usuario.getDataNascimento();

        Call<Usuario> call = new RetrofitConfig().getUserService().cadastraUsuario(email, nome,
                sobrenome, senha, cpf, sexo, dataNasc);
        try {
            result = call.execute().body();
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }


}
