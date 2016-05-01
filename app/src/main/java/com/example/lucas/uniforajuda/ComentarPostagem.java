package com.example.lucas.uniforajuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;
import com.example.lucas.uniforajuda.helper.ComentarioHelper;

public class ComentarPostagem extends AppCompatActivity {


    private ComentarioHelper comentarioHelper;
    private Button btSalvarC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_postagem);

        PostagemBean postagemBean = getIntent().getParcelableExtra("postagem");

        comentarioHelper =  new ComentarioHelper(this,postagemBean);

        btSalvarC = (Button) findViewById(R.id.btSalvarPostagemC);

        btSalvarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostagemBean postagemBean1 =  comentarioHelper.getPostagem();
                PostagemDAO postagemDAO = new PostagemDAO(ComentarPostagem.this);
                postagemDAO.atualizarRegistroPostagem(postagemBean1);
                postagemDAO.close();

                Intent intent = new Intent(ComentarPostagem.this, MenuPrincipal.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
