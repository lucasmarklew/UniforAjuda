package com.example.lucas.uniforajuda;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;

import java.util.List;

public class ListarPostagens extends AppCompatActivity {

    public static final String TAG_S = "SELECAO_POSTAGEM";

    private PostagemBean postagemSelecionada;
    private ListView ListViewPostagens;
    private List<PostagemBean> listaPostagensBean;
    private ArrayAdapter<PostagemBean> adaptador;
    private int adaptadorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_postagens);



        ListViewPostagens =  (ListView) findViewById(R.id.listViewPostagens);
        PostagemDAO postagemDAO =  new PostagemDAO(ListarPostagens.this);
        listaPostagensBean = postagemDAO.recuperarRegistros();

        if (listaPostagensBean.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.create();
            dialog.setTitle("Não existe nenhuma postagem cadastrada");
            dialog.show();

        }else {

            adaptadorLayout = android.R.layout.simple_list_item_1;
            adaptador = new ArrayAdapter<PostagemBean>(this, adaptadorLayout, listaPostagensBean) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    ((TextView) view).setTextColor(Color.WHITE);
                    return view;
                }
            };

            ListViewPostagens.setAdapter(adaptador);

            registerForContextMenu(ListViewPostagens);


            ListViewPostagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(ListarPostagens.this, ComentarPostagem.class);
                    Parcelable parceable = (Parcelable) ListViewPostagens.getItemAtPosition(position);
                    intent.putExtra("postagem", parceable);
                    startActivity(intent);

                }
            });

            // evento longo que utiliza o MenuContext
            /*
            ListViewPostagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int posicao, long id) {
                    postagemSelecionada = (PostagemBean) adaptador.getItem(posicao);

                    Log.i(TAG_S, "Postagem Selecionada ListView.longClick"
                            + postagemSelecionada.getTitulo());
                    return false;
                }
            });
            */
        }
    }
}
