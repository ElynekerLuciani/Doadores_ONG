package com.example.doacao_ong;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tipo = "admin";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tipo = extras.getString("tipo");
        } else {
            System.out.println("Extras é NULL");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

//        CONFIGURANDO MENU DO DRAWER
        if (tipo.equals("usuario")) {
            navigationView.getMenu().setGroupVisible(R.id.menu_usuario, true);
            navigationView.getMenu().setGroupVisible(R.id.menu_admin, false);
        } else {
            navigationView.getMenu().setGroupVisible(R.id.menu_usuario, false);
            navigationView.getMenu().setGroupVisible(R.id.menu_admin, true);
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_dashboard, R.id.nav_meu_perfil,
                R.id.nav_lancar_saida, R.id.nav_doacoes_recebidas, R.id.nav_doacoes_realizadas, R.id.nav_usuario_doacao).setDrawerLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

//        CONFIGURANDO TELA INICIAL DO DRAWER
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);

        if (tipo.equals("usuario")) {
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}