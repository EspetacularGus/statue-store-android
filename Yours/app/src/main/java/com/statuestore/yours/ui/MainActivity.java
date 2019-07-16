package com.statuestore.yours.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.statuestore.yours.R;
import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.ui.compra.CustomActivity;
import com.statuestore.yours.ui.fragments.MainFragment;
import com.statuestore.yours.ui.login.LoginActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCustom();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        TextView lblNomeUsuario = navigationView.getHeaderView(0)
                .findViewById(R.id.nomeUsuario);
        TextView lblEmailUsuario = navigationView.getHeaderView(0)
                .findViewById(R.id.emailUsuario);
        lblNomeUsuario.setText(LoggedInUser.getNome());
        lblEmailUsuario.setText(LoggedInUser.getEmail());

        ft.add(R.id.fragment_content, new MainFragment());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Inicio) {
            ft.replace(R.id.fragment_content, new MainFragment());
        } else if (id == R.id.Pedidos) {
            redirectWeb(this.getString(R.string.orders_website));
        } else if (id == R.id.Conta){
            redirectWeb(this.getString(R.string.account_website));
        } else if (id == R.id.Site) {
            redirectWeb(this.getString(R.string.home_website));
        } else if (id == R.id.Sair) {
            finish();
            startMenu();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startCustom() {
        Intent intent = new Intent(this, CustomActivity.class);
        startActivity(intent);
    }

    private void startMenu() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void redirectWeb(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
