package com.example.lucas.uniforajuda;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {



    //testando meu GitHub

    private Button btHistorico;
    private Button btPostagem;
    private Button btMeuHistorico;

    @Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        return true;
    }*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.setVisible(true);
        //Bundle bundle = getIntent().getExtras();
        //ActionBar actionBar = getActionBar();
        //actionBar.setSubtitle("https://zarelli.wordpress.com");
       // actionBar.setTitle("Exemplo - ActionBar");



        btHistorico = (Button) findViewById(R.id.btHistorico);
        btPostagem = (Button) findViewById(R.id.btPostagem);
        btMeuHistorico = (Button) findViewById(R.id.btMeuHistorico);


        btPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Postagem.class);
                startActivity(intent);


            }
        });

        btHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, ListarPostagens.class);
                startActivity(intent);

            }
        });

    }
}