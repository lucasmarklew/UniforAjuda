package com.example.lucas.uniforajuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.uniforajuda.bean.UsuarioBean;

public class MenuPrincipal extends AppCompatActivity {


    private Button btPesquisa;
    private Button btPostagem;
    private Button btMeuHistorico;
    private Button btLogaut;
    private TextView textViewUsuarioLogado;
    private  int id_reserva;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        this.setVisible(true);
        final Bundle bundle = getIntent().getExtras();


        textViewUsuarioLogado = (TextView) findViewById(R.id.usuario_logado);
        btMeuHistorico = (Button) findViewById(R.id.btMinhasPostagens);
        btPostagem = (Button) findViewById(R.id.btPostagem);
        btPesquisa = (Button) findViewById(R.id.btPesquisa);
        btLogaut = (Button)findViewById(R.id.button_logaut);
        textViewUsuarioLogado.setText(bundle.getString("nome"));

        id_reserva = Integer.parseInt(bundle.getString("id"));
        UsuarioBean usuario = new UsuarioBean();
        usuario.setNome(bundle.getString("nome"));
        usuario.setMatricula(bundle.getString("matricula"));
        usuario.setId(id_reserva);

        btLogaut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuPrincipal.this, Logar.class);
                startActivity(intent);
                finish();
            }
        });

        btPostagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, Postagem.class);
                intent.putExtra("id", bundle.getString("id"));

                startActivity(intent);


            }
        });

        btMeuHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuPrincipal.this, ListarPostagens.class);
                intent.putExtra("id", bundle.getString("id"));

                startActivity(intent);

            }
        });



        btPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipal.this, PesquisarPostagem.class);
                intent.putExtra("id", bundle.getString("id"));
                startActivity(intent);

            }
        });


    }
}