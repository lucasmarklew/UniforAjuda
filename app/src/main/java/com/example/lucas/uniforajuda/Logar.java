package com.example.lucas.uniforajuda;
/**
 * Classe modificada para autenticar o login de usuario Arley Oliveira
 * metodos genericos,porém ainda irei deixar mais seguro o caso de autenticar login
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.uniforajuda.bean.UsuarioBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;

import java.util.ArrayList;

import static com.example.lucas.uniforajuda.dao.PostagemDAO.*;

public class Logar extends AppCompatActivity {

    EditText matricula;
    EditText senha;
    Button btLogar;
    String SenhaDigitada;
    String MatriculaDigitada;
    ArrayList<UsuarioBean> listaUsuarios;
    PostagemDAO dao = new PostagemDAO(this);
    Boolean liberador = false;
    UsuarioBean usuarioIntent;
    String id_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logar);

        matricula = (EditText) findViewById(R.id.etMatricula);
        senha = (EditText) findViewById(R.id.etSenha);
        btLogar = (Button) findViewById(R.id.btLogar);


        //PostagemDAO dao = new PostagemDAO(this);
        //listaUsuarios = dao.selectInstances();
        dao.loadDataBase();
        listaUsuarios = dao.selectInstances();


        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SenhaDigitada = senha.getText().toString();
                MatriculaDigitada = matricula.getText().toString();

                liberador = validaLogin(MatriculaDigitada, SenhaDigitada);
                usuarioIntent = capturaUsuarioBD(MatriculaDigitada, SenhaDigitada);
                if (liberador.equals(true)) {
                    Intent intent = new Intent(Logar.this, MenuPrincipal.class);
                    id_string = Integer.toString(usuarioIntent.getId());
                    intent.putExtra("id",id_string);
                    intent.putExtra("nome",usuarioIntent.getNome());
                    intent.putExtra("matricula",usuarioIntent.getMatricula());
                    startActivity(intent);

                    finish();

                } else {

                    Toast.makeText(Logar.this, "Matricula ou Senha invalida " +
                            " ", Toast.LENGTH_SHORT).show();

                }


            }

        });


    }
    public UsuarioBean capturaUsuarioBD(String matricula, String senha){
        UsuarioBean user = dao.findByLogin(matricula, senha);
        return user;
    }


    public boolean validaLogin(String matricula, String senha) {
        UsuarioBean user = dao.findByLogin(matricula, senha);
        if (user == null || user.getMatricula() == null || user.getSenha() == null) {
            return false;
        }
        String informado = matricula + senha;
        String esperado = user.getMatricula() + user.getSenha();
        if (informado.equals(esperado)) {
            return true;
        }
        return false;
    }


}
