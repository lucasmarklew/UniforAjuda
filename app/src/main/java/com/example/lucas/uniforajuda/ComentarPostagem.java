package com.example.lucas.uniforajuda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.bean.RespostaBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;
import com.example.lucas.uniforajuda.helper.ComentarioHelper;

public class ComentarPostagem extends AppCompatActivity {


    private ComentarioHelper comentarioHelper;
    private Button btSalvarC;
    private String id_string;
    private int id_inteiro;
    private Button btRetornaMenu;
    private String titulo;
    private String postagem;
    private EditText campoTexto;
    private RespostaBean resposta;
    private String resposta_ok;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_postagem);
        campoTexto= (EditText)findViewById(R.id.etPostagemC);
         bundle = getIntent().getExtras();


        id_string = bundle.getString("id");
        id_inteiro = Integer.parseInt(id_string);
        btRetornaMenu = (Button)findViewById(R.id.botao_retornar);
        btRetornaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btSalvarC = (Button) findViewById(R.id.btSalvarComentario);

        btSalvarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resposta = new RespostaBean();
                resposta.setId(id_inteiro);

                resposta.setResposta(campoTexto.getText().toString());


                //resposta.setResposta("casa");

               /* Toast.makeText(ComentarPostagem.this,
                        "id da  postagem  recebida" +resposta.getId()
                               , Toast.LENGTH_SHORT).show();*/

                PostagemDAO postagemDAO = new PostagemDAO(ComentarPostagem.this);
                postagemDAO.registrarResposta(resposta);

                //Intent intent = new Intent(ComentarPostagem.this, MenuPrincipal.class);
               // startActivity(intent);
                finish();

            }
        });
    }
}
