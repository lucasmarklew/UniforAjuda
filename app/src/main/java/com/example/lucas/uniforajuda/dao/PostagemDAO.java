package com.example.lucas.uniforajuda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.bean.UsuarioBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 30/04/2016.
 * Obs feita por Arley Oliveira: como não teve relacionamento ou criação a mais de uma tabela,foi necesseário
 * realizar algumas alterações, incluir uma tabela chamada usuario para realizar inserção de usuario no sistema
 * e  autenticar entrada do Usuario,no caso vai ser necessario ainda criar o relacionamento.
 */
public class PostagemDAO extends SQLiteOpenHelper {
    /*adcionado por Arley*/
    public static final String TABELA_USUARIO = "USUARIO";
    private static final String TAG_S = "SELECIONAR REGISTROS";
    private static final String TAG_I_USUARIO = "INSERIR_USUARIO";
    private static final String TAG_V = "VERIFICANDO_LOGIN";
    private static final String TAG_LOGIN_OK = "LOGIN AUTORIZADO";
    private static final String TAG_ERRO_AUTENTICAR = "LOGIN NÃO REALIZADO";
    /*adcionado por Arley*/

    public static final int VERSAO = 1;
    public static final String TABELA = "postagens";
    public static final String DATABASE = "BD_POSTAGEM";

    private static final String TAG_I = "INSERIR_POSTAGENS";
    private static final String TAG_L = "LISTAR_POSTAGENS";
    private static final String TAG_R = "REMOVER_POSTAGENS";




    public PostagemDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA
                + "('id' INTEGER PRIMARY KEY NOT NULL , "
                + "'titulo' TEXT NOT NULL"
                + ",'postagem' TEXT)";

        String sql2 = "CREATE TABLE " + TABELA_USUARIO
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT,"
                + "matricula TEXT,"
                + "senha TEXT);";


        db.execSQL(sql);
        db.execSQL(sql2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS "+ TABELA;
        db.execSQL(sql);
        onCreate(db);

    }
    /**/
    //Metodo incluidos por Arley para inserir usuario ao banco
    public void inserirUsuario(UsuarioBean bean){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", bean.getNome());
        contentValues.put("matricula", bean.getMatricula());
        contentValues.put("senha", bean.getMatricula());
        getWritableDatabase().insert(TABELA_USUARIO, null, contentValues);

        Log.i(TAG_I_USUARIO, "O registro de id: " + bean.getNome() + " foi inserido com sucesso");


    }
    public ArrayList<UsuarioBean> selectInstances() {

        // Passo 01 - Criar o arraylist de UsuarioBean
        ArrayList<UsuarioBean> listaUsuarios = new ArrayList<UsuarioBean>();

        // Passo 02 - Criar o SQL para selecionar os registros do banco
        String sql = "SELECT * FROM " + TABELA_USUARIO;

        // Passo 03 - Recuperar os registros
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // Passo 04 - Percorrer o cursor e salvar os registros de UsuarioBean
        while (cursor.moveToNext()) {
            // Criação da instancia de usuariobean utilizando informações
            // provenientes da base de dados
            UsuarioBean usuariobean = new UsuarioBean();

            // Construindo o objeto a partir dos registros da base de dados
            usuariobean.setId(cursor.getInt(0));
            usuariobean.setNome(cursor.getString(1));
            usuariobean.setMatricula(cursor.getString(2));
            usuariobean.setSenha(cursor.getString(3));


            // Adicionando a instancia de usuario a lista de usuarios
            listaUsuarios.add(usuariobean);

            Log.i(TAG_S, "O registro de id: "+usuariobean.getNome()+" foi selecionado");

        }

        return listaUsuarios;
    }
    public void loadDataBase(){
        UsuarioBean bean = new UsuarioBean();
        bean.setNome("arley");
        bean.setMatricula("123");
        bean.setSenha("123");

        UsuarioBean bean2 = new UsuarioBean();
        bean2.setNome("lucas");
        bean2.setMatricula("321");
        bean2.setSenha("321");

        UsuarioBean bean3 = new UsuarioBean();
        bean3.setNome("mateus");
        bean3.setMatricula("012");
        bean3.setSenha("012");

        inserirUsuario(bean);
        inserirUsuario(bean2);
        inserirUsuario(bean3);
    }

    public UsuarioBean montaUsuario(Cursor cursor) {
        if (cursor.getCount() == 0) {

            Log.i(TAG_ERRO_AUTENTICAR, "DADOS FORNECIDO INVÁLIDOS");

            return null;
        }
        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String matricula = cursor.getString(cursor.getColumnIndex("matricula"));
        String senha = cursor.getString(cursor.getColumnIndex("senha"));

        UsuarioBean novo = new UsuarioBean();
        novo.setNome(nome);
        novo.setId(id);
        novo.setMatricula(matricula);
        novo.setSenha(senha);
        Log.i(TAG_LOGIN_OK, "Usuario : " + nome + " e matricula :" + matricula);

        return novo;
    }


    public  UsuarioBean findByLogin(String matricula, String senha) {
        String sql = "SELECT * FROM " + TABELA_USUARIO + " WHERE matricula = ? AND senha = ?";
        String[] selectionArgs = new String[] { matricula, senha };
        //Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        Log.i(TAG_V, "Buscando ,Matricula : " + matricula + " e senha :"+senha);
        Cursor cursor = getReadableDatabase().rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        return montaUsuario(cursor);
    }




    /**/
    //Fim de Metodo implementado por Arley
    public void registrarPostagem(PostagemBean postagem){

        ContentValues valores = new ContentValues();

        valores.put("titulo", postagem.getTitulo().toString());
        valores.put("postagem", postagem.getPostagem().toString());

        getWritableDatabase().insert(TABELA, null, valores);

        Log.i(TAG_I, "Registro realizado: " + postagem.getTitulo());

    }


    public void atualizarRegistroPostagem(PostagemBean postagem){

        ContentValues valores = new ContentValues();

        valores.put("titulo", postagem.getTitulo().toString());
        valores.put("postagem", postagem.getPostagem().toString());

        String[] args = new String[]{Long.toString(postagem.getId())};

        getWritableDatabase().update(TABELA, valores, "id=?", args);

        Log.i(TAG_I, "Postagem atualizada: "+ postagem.getTitulo());

    }


    public List<PostagemBean> recuperarRegistros(){

        List<PostagemBean> listaPostagens = new ArrayList<PostagemBean>();

        String sql = "Select * from postagens";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        try{
            while(cursor.moveToNext()){

                PostagemBean postagem = new PostagemBean();

                postagem.setId(cursor.getLong(0));
                postagem.setTitulo(cursor.getString(1));
                postagem.setPostagem(cursor.getString(2));

                listaPostagens.add(postagem);
            }
        }catch(SQLException sqle){
            Log.e(TAG_L, sqle.getMessage());
        }finally{
            cursor.close();
        }

        return listaPostagens;
    }


    public void removerRegistroPostagem(PostagemBean postagem){
        String [] args = {postagem.getId().toString()};

        getWritableDatabase().delete(TABELA, "id=?", args);

        Log.i(TAG_R, "Postagem removida: "+ postagem.getTitulo());
    }



}
