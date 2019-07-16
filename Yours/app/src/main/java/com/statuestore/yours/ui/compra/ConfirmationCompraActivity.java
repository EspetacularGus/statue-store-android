package com.statuestore.yours.ui.compra;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.statuestore.yours.R;
import com.statuestore.yours.ui.MainActivity;

public class ConfirmationCompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_compra);

        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                redirectTo(new MainActivity());
            }
        });

        TextView textAcompanhe = findViewById(R.id.textAcompanhe);
        textAcompanhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectWeb(ConfirmationCompraActivity.this.getString(R.string.orders_website));
            }
        });
    }

    private void redirectTo(Activity activity) {
        Intent intent = new Intent(this, activity.getClass());
        startActivity(intent);
    }

    private void redirectWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
