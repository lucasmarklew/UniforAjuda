package com.example.lucas.uniforajuda.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ArleyOliveira on 02/05/2016.
 * Classe referente a objeto do tipo Usuario o qual será necessário carregar ou criar na hora o qual o objeto de for buscado
 * no banco para realizar login
 */
public class UsuarioBean {


    private int id;
    private String nome;
    private String matricula;
    private String senha;

    public UsuarioBean() {
    }

    public UsuarioBean(Parcel in) {

        readFromParcelable(in);
    }
    private void readFromParcelable(Parcel in) {

        id = in.readInt();
        nome = in.readString();
        matricula = in.readString();
        senha = in.readString();

    }

    public String toString() {

        return this.nome;
    }
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(nome);
        dest.writeString(matricula);
        dest.writeString(senha);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UsuarioBean createFromParcel(Parcel in) {
            return new UsuarioBean(in);
        }

        public UsuarioBean[] newArray(int size) {
            return new UsuarioBean[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
