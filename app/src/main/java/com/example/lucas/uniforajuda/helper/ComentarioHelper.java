package com.example.lucas.uniforajuda.helper;

import android.widget.EditText;

import com.example.lucas.uniforajuda.ComentarPostagem;
import com.example.lucas.uniforajuda.Postagem;
import com.example.lucas.uniforajuda.R;
import com.example.lucas.uniforajuda.bean.PostagemBean;

/**
 * Created by Lucas on 01/05/2016.
 */
public class ComentarioHelper {

    private Long IdC;
    private EditText etTituloC;
    private EditText etPostagemC;

    private PostagemBean postagem;


    public ComentarioHelper (ComentarPostagem atividade, PostagemBean extras){

        this.etTituloC = (EditText) atividade.findViewById(R.id.etTituloC);
        this.etPostagemC = (EditText) atividade.findViewById(R.id.etPostagemC);

        IdC = extras.getId();
        etTituloC.setText(extras.getTitulo());
        etPostagemC.setText(extras.getPostagem());

        this.postagem = this.getPostagem();

    }

    public PostagemBean getPostagem(){

        PostagemBean postagem = new PostagemBean();

        postagem.setId(IdC);
        postagem.setTitulo(etTituloC.getText().toString());
        postagem.setPostagem(etPostagemC.getText().toString());

        return postagem;
    }
}
