package com.example.lucas.uniforajuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;
import com.example.lucas.uniforajuda.helper.PostagemHelper;

public class Postagem extends AppCompatActivity {


    private PostagemHelper postagemHelper;
    private Button btSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postagem);

        postagemHelper = new PostagemHelper(this);
        btSalvar = (Button) findViewById(R.id.btSalvarPostagem);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostagemBean postagemBean =  postagemHelper.getPostagem();
                PostagemDAO postagemDAO = new PostagemDAO(Postagem.this);
                postagemDAO.registrarPostagem(postagemBean);
                postagemDAO.close();

                Intent intent = new Intent(Postagem.this, MenuPrincipal.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
