package com.example.lucas.uniforajuda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.lucas.uniforajuda.bean.PostagemBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 30/04/2016.
 */
public class PostagemDAO extends SQLiteOpenHelper {

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
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS "+ TABELA;
        db.execSQL(sql);
        onCreate(db);

    }

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
