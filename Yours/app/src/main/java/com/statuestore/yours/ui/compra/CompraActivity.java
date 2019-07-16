package com.statuestore.yours.ui.compra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.statuestore.yours.R;
import com.statuestore.yours.data.control.ProdutoController;
import com.statuestore.yours.data.helpers.FileIO;
import com.statuestore.yours.data.model.Produto;
import com.statuestore.yours.ui.MainActivity;

public class CompraActivity extends AppCompatActivity {

    public static Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        ImageView imageModelo = findViewById(R.id.imgCamiseta);
        final Bitmap img = FileIO.getImageFrom(produto.getImgModelo());
        imageModelo.setImageBitmap(img);

        Button btnContinuar = findViewById(R.id.btnContinuar2);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MainActivity.class);
            }
        });

        ImageView download = findViewById(R.id.iconDownload);
        download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showToast("Salvando imagem.");
                FileIO.saveImg("download", img);
            }
        });

        ImageView delete = findViewById(R.id.iconDelete);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        boolean result = new ProdutoController().deleteProduto(produto.getId());
                        if (result) {
                            showToast("Deletando produto.");
                            goTo(MainActivity.class);
                        } else {
                            showToast("Erro ao deletar produto.");
                        }
                    }
                }).start();
            }
        });
    }

    private void goTo(Class activity) {
        finish();
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void showToast(String message) {
        final String msg = message;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CompraActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onContinuarClicked() {
        /*
        LinearLayout layoutCompra = findViewById(R.id.layoutA);
        LinearLayout layoutEndereco = findViewById(R.id.layoutB);
        LinearLayout layoutPagamento = findViewById(R.id.layoutC);

        if (layoutCompra.getVisibility() == View.VISIBLE) {
            layoutCompra.setVisibility(View.INVISIBLE);
            layoutEndereco.setVisibility(View.VISIBLE);
        } else if (layoutEndereco.getVisibility() == View.VISIBLE) {
            layoutEndereco.setVisibility(View.INVISIBLE);
            layoutPagamento.setVisibility(View.VISIBLE);
        } else if (layoutPagamento.getVisibility() == View.VISIBLE) {
            layoutPagamento.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, ConfirmationCompraActivity.class);
            startActivity(intent);
            finish();
        }*/
    }
}
