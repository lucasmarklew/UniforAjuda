package com.example.lucas.uniforajuda.helper;

import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.uniforajuda.ComentarPostagem;
import com.example.lucas.uniforajuda.Postagem;
import com.example.lucas.uniforajuda.R;
import com.example.lucas.uniforajuda.bean.PostagemBean;

/**
 * Created by Lucas on 01/05/2016.
 */
public class ComentarioHelper {

    private int IdC;
    private EditText etTituloC;
    private EditText etPostagemC;
    //private TextView textViewPost;

    private PostagemBean postagem;


    public ComentarioHelper (ComentarPostagem atividade, PostagemBean extras){

        //this.etTituloC = (EditText) atividade.findViewById(R.id.etTituloC);
        this.etPostagemC = (EditText) atividade.findViewById(R.id.etPostagemC);
        //this.textViewPost = (TextView) atividade.findViewById(R.id.textViewPost);

       // IdC = extras.getId();
       // etTituloC.setText(extras.getTitulo());
        //etPostagemC.setText(extras.getPostagem());
        //textViewPost.setText(extras.getPostagem());

        this.postagem = this.getPostagem();

    }

    public PostagemBean getPostagem(){

        PostagemBean postagem = new PostagemBean();

        postagem.setId(IdC);
        postagem.setTitulo(etTituloC.getText().toString());
        postagem.setPostagem(etPostagemC.getText().toString());
       //postagem.setPostagem(textViewPost.getText().toString());

        return postagem;
    }
}
