package com.statuestore.yours.data.control;

import com.statuestore.yours.data.model.Usuario;
import retrofit2.Call;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginController {

    @SuppressWarnings("unchecked")
    public Usuario login(String email, String senha) {

        Usuario result;

        Call<Usuario> call = new RetrofitConfig().getUserService().validaUsuario(email, senha);
        try {
            result = call.execute().body();
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
