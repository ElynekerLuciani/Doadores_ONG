package com.example.doacao_ong;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.doacao_ong.ui.login.LoginActivity;

import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        TextView labelNome = header.findViewById(R.id.nav_header_label_nome);
        TextView labelEmail = header.findViewById(R.id.nav_header_label_email);

        labelNome.setText(UsuarioFirebase.getInstance().getNome());
        labelEmail.setText(UsuarioFirebase.getInstance().getEmail());

//        CONFIGURANDO MENU DO DRAWER
        if (UsuarioFirebase.getInstance().getTipo().equals("Doador")) {
            navigationView.getMenu().setGroupVisible(R.id.menu_usuario, true);
            navigationView.getMenu().setGroupVisible(R.id.menu_admin, false);
        } else {
            navigationView.getMenu().setGroupVisible(R.id.menu_usuario, false);
            navigationView.getMenu().setGroupVisible(R.id.menu_admin, true);
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_dashboard, R.id.nav_meu_perfil,
                R.id.nav_nova_despesa, R.id.nav_doacoes_recebidas, R.id.nav_despesas, R.id.nav_usuario_doacao).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

//        CONFIGURANDO TELA INICIAL DO DRAWER
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);

        if (UsuarioFirebase.getInstance().getTipo().equals("Doador")) {
            navGraph.setStartDestination(R.id.nav_usuario_doacao);
        } else {
            navGraph.setStartDestination(R.id.nav_dashboard);
        }

        navController.setGraph(navGraph);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_sign_out) {
            ConfiguracaoFirebase.getFirebaseAutenticacao().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}