package com.example.lucas.uniforajuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;
import com.example.lucas.uniforajuda.helper.PostagemHelper;

public class Postagem extends AppCompatActivity {


    private PostagemHelper postagemHelper;
    private Button btSalvar;
    private Button btRetornaMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postagem);

        postagemHelper = new PostagemHelper(this);
        btSalvar = (Button) findViewById(R.id.btSalvarPostagem);
        final Bundle bundle = getIntent().getExtras();
        btRetornaMenu = (Button)findViewById(R.id.botao_retornar);
        btRetornaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostagemBean postagemBean =  postagemHelper.getPostagem();
                PostagemDAO postagemDAO = new PostagemDAO(Postagem.this);
                postagemDAO.registrarPostagem(postagemBean,bundle.getString("id"));

                postagemDAO.close();
                onBackPressed();

            }
        });


    }
}
