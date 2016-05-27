package com.example.lucas.uniforajuda.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lucas on 30/04/2016.
 */

/**
 * Teste Commit Mateus.
 */

/**
 * Tste de COmit Arley Oliveira
 *
 */

public class PostagemBean implements Parcelable {



    private int id;
    private String titulo;
    private String postagem;

    public PostagemBean() {
    }

    public PostagemBean(Parcel in) {

        readFromParcelable(in);
    }

    private void readFromParcelable(Parcel in) {

        id = in.readInt();
        titulo = in.readString();
        postagem = in.readString();

    }

    @Override
    public String toString() {

        return this.titulo;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(titulo);
        dest.writeString(postagem);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostagemBean createFromParcel(Parcel in) {
            return new PostagemBean(in);
        }

        public PostagemBean[] newArray(int size) {
            return new PostagemBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPostagem() {
        return postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }
}


