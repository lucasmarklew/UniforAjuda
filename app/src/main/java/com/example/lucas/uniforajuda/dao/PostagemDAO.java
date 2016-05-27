package com.example.lucas.uniforajuda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lucas.uniforajuda.Postagem;
import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.bean.RespostaBean;
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
    public static final String TABELA_RESPOSTA = "RESPOSTA";
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

        String sql2 = "CREATE TABLE " + TABELA_USUARIO
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT,"
                + "matricula TEXT,"
                + "senha TEXT);";

        String sql = "CREATE TABLE " + TABELA
                + "( id INTEGER PRIMARY KEY,"
                + " id_postagem INTEGER,"
                + " titulo TEXT,"
                + " postagem  TEXT,"
                +"  FOREIGN KEY(id_postagem) REFERENCES "+TABELA_USUARIO+"(id))";

        String sql0 = "CREATE TABLE " + TABELA_RESPOSTA
                + "( id INTEGER PRIMARY KEY,"
                + " id_pergunta INTEGER,"
                + " resposta TEXT,"
                +"  FOREIGN KEY(id_pergunta) REFERENCES "+TABELA+"(id_postagem))";




        db.execSQL(sql2);
        db.execSQL(sql);
        db.execSQL(sql0);



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

    public ArrayList<PostagemBean> selectInstancesPostagens() {
        ArrayList<PostagemBean> listaPostagens = new ArrayList<PostagemBean>();
        String sql = "SELECT * FROM " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            PostagemBean postagemBean = new PostagemBean();

            // Construindo o objeto a partir dos registros da base de dados
            postagemBean.setId(cursor.getInt(0));
            postagemBean.setTitulo(cursor.getString(2));
            postagemBean.setPostagem(cursor.getString(3));



            // Adicionando a instancia de usuario a lista de usuarios
            listaPostagens.add(postagemBean);

            //Log.i(TAG_S, "O registro de id: "+postagemBean.get()+" foi selecionado");

        }

        return listaPostagens;
    }

    public ArrayList<RespostaBean> selectInstancesResposas(String id) {
        ArrayList<RespostaBean> listaRespostas = new ArrayList<RespostaBean>();
        //String sql = "SELECT * FROM " + TABELA_RESPOSTA;
       // String sql = "SELECT  id,resposta FROM " + TABELA_RESPOSTA ;
        //String sql = "SELECT  id_pergunta,resposta FROM " + TABELA_RESPOSTA ;ok

        String sql = "SELECT  id_pergunta,resposta FROM " + TABELA_RESPOSTA + " WHERE id_pergunta  = "+id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Log.i(TAG_S, "Dados encontrados na tabela respostas" +
                    ": ");
            RespostaBean resposta = new RespostaBean();

            // Construindo o objeto a partir dos registros da base de dados
            resposta.setId(cursor.getInt(0));
            resposta.setResposta(cursor.getString(1));
            //resposta.setPostagem(cursor.getString(3));



            // Adicionando a instancia de usuario a lista de usuarios
            listaRespostas.add(resposta);

            //Log.i(TAG_S, "O registro de id: "+postagemBean.get()+" foi selecionado");

        }

        return listaRespostas;
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
        Log.i(TAG_LOGIN_OK, "Usuario : " + nome + " id :" + id + " e matricula :" + matricula);

        return novo;
    }


    public  UsuarioBean findByLogin(String matricula, String senha) {
        String sql = "SELECT * FROM " + TABELA_USUARIO + " WHERE matricula = ?   and senha = ? ";
        String[] selectionArgs = new String[] { matricula, senha };
        //Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        Log.i(TAG_V, "Buscando ,Matricula : " + matricula + " e senha :" + senha);
        Cursor cursor = getReadableDatabase().rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        return montaUsuario(cursor);
    }




    public void registrarPostagem(PostagemBean postagem,String id_usuario){

        ContentValues valores = new ContentValues();

        valores.put("titulo", postagem.getTitulo().toString());
        valores.put("postagem", postagem.getPostagem().toString());
        valores.put("id_postagem",id_usuario);

        getWritableDatabase().insert(TABELA, null, valores);

        Log.i(TAG_I, "Registro realizado,titulo : " + postagem.getTitulo());
        Log.i(TAG_I, "Registro realizado: " + postagem.getPostagem());

    }










    public void registrarResposta(RespostaBean resposta){
    //public void registrarResposta(RespostaBean resposta,String id_usuario){
        String id_resposta = Integer.toString(resposta.getId());
        ContentValues valores = new ContentValues();
        valores.put("resposta", resposta.getResposta());
        valores.put("id_pergunta", id_resposta);
       // valores.put("id_pergunta", id_resposta);
        //valores.put("resposta", resposta.getResposta());
         getWritableDatabase().insert(TABELA_RESPOSTA, null, valores);

        Log.i(TAG_I, "Registro realizado,resposta : " + resposta.getResposta());
        Log.i(TAG_I, "Registro realizado no id da tabela id : " + id_resposta);

    }





    public void atualizarRegistroPostagem(PostagemBean postagem){

        ContentValues valores = new ContentValues();

        valores.put("titulo", postagem.getTitulo().toString());
        valores.put("postagem", postagem.getPostagem().toString());

        String[] args = new String[]{Long.toString(postagem.getId())};

        getWritableDatabase().update(TABELA, valores, "id=?", args);

        Log.i(TAG_I, "Postagem atualizada: "+ postagem.getTitulo());

    }


    public List<PostagemBean> recuperarRegistros(String id_usuario){

        List<PostagemBean> listaPostagens = new ArrayList<PostagemBean>();
        String sql = "SELECT id,titulo,postagem  FROM " + TABELA + " WHERE id_postagem  = "+id_usuario;
        //String sql = "SELECT titulo,postagem  FROM " + TABELA + " WHERE id_postagem  = 2";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        Log.i(TAG_S, "Buscando dados.... : ");
        try{
            while(cursor.moveToNext()){
                Log.i(TAG_S, "Dados encontrados : ");
                PostagemBean postagem = new PostagemBean();

                postagem.setId(cursor.getInt(0));
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
        Integer id = postagem.getId();
        String [] args = {id.toString()};

        getWritableDatabase().delete(TABELA, "id=?", args);

        Log.i(TAG_R, "Postagem removida: "+ postagem.getTitulo());
    }

    public ArrayList<RespostaBean> recuperaTodosAsRespostas(){

        ArrayList<RespostaBean> listaRespostas = new ArrayList<RespostaBean>();
        String sql = "SELECT * FROM " + TABELA_RESPOSTA ;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        Log.i(TAG_S, "Buscando dados.... : ");
        try{
            while(cursor.moveToNext()){
                Log.i(TAG_S, "Dados encontrados : ");
                RespostaBean postagem = new RespostaBean();

                postagem.setId(cursor.getInt(0));
                postagem.setResposta(cursor.getString(2));
                //postagem.setPostagem(cursor.getString(1));

                listaRespostas.add(postagem);
            }
        }catch(SQLException sqle){
            Log.e(TAG_L, sqle.getMessage());
        }finally{
            cursor.close();
        }

        return listaRespostas;
    }

    public List<PostagemBean> recuperaTodosOsRegistros(){

        List<PostagemBean> listaPostagens = new ArrayList<PostagemBean>();
        String sql = "SELECT * FROM " + TABELA ;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        Log.i(TAG_S, "Buscando dados.... : ");
        try{
            while(cursor.moveToNext()){
                Log.i(TAG_S, "Dados encontrados : ");
                PostagemBean postagem = new PostagemBean();

                postagem.setId(cursor.getInt(0));
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


}
