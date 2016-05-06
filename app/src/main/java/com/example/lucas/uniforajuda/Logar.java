package com.example.lucas.uniforajuda;
/**
 * Classe modificada para autenticar o login de usuario Arley Oliveira
 * metodos genericos,porém ainda irei deixar mais seguro o caso de autenticar login
 *
 *
 *
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lucas.uniforajuda.bean.UsuarioBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;

import java.util.ArrayList;

public class Logar extends AppCompatActivity {

    EditText matricula;
    EditText senha;
    Button btLogar;
    String SenhaDigitada;
    String MatriculaDigitada;
    ArrayList<UsuarioBean>  listaUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        matricula = (EditText) findViewById(R.id.etMatricula);
        senha = (EditText) findViewById(R.id.etSenha);
        btLogar = (Button) findViewById(R.id.btLogar);


        PostagemDAO dao = new PostagemDAO(this);
        //listaUsuarios = dao.selectInstances();
        dao.loadDataBase();
        listaUsuarios = dao.selectInstances();


        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SenhaDigitada = senha.getText().toString();
                MatriculaDigitada = matricula.getText().toString();

                for (int i=0; i<listaUsuarios.size(); i++){
                    UsuarioBean novo = new UsuarioBean();

                    novo = listaUsuarios.get(i);

                    if ( (SenhaDigitada.equals(novo.getSenha())) &&
                            (MatriculaDigitada.equals(novo.getMatricula())  ) ){
                        Intent intent = new Intent(Logar.this, MenuPrincipal.class);
                        startActivity(intent);
                        finish();

                    }
                    else {

                        /*Toast.makeText(Logar.this,"Matricula ou Senha invalida " + SenhaDigitada +
                                " ",Toast.LENGTH_SHORT).show();*/

                    }


                }





            }

        });



    }
}
