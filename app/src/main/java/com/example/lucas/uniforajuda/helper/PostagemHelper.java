package com.example.lucas.uniforajuda.helper;

import android.widget.Button;
import android.widget.EditText;

import com.example.lucas.uniforajuda.Postagem;
import com.example.lucas.uniforajuda.R;
import com.example.lucas.uniforajuda.bean.PostagemBean;

/**
 * Created by Lucas on 30/04/2016.
 */
public class PostagemHelper {

    private EditText etTitulo;
    private EditText etPostagem;

    private PostagemBean postagem;


    public PostagemHelper (Postagem atividade){

        this.etTitulo = (EditText) atividade.findViewById(R.id.etTitulo);
        this.etPostagem = (EditText) atividade.findViewById(R.id.etPostagem);

        this.postagem = this.getPostagem();

    }

    public PostagemBean getPostagem(){

        PostagemBean postagem = new PostagemBean();

        postagem.setTitulo(etTitulo.getText().toString());
        postagem.setPostagem(etPostagem.getText().toString());

        return postagem;
    }

}
