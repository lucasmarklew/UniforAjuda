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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.uniforajuda.bean.PostagemBean;
import com.example.lucas.uniforajuda.dao.PostagemDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarPostagens extends AppCompatActivity {

    public static final String TAG_S = "SELECAO_POSTAGEM";

    private PostagemBean postagemSelecionada;
    private ListView ListViewPostagens;
    private ArrayList<PostagemBean> listaPostagensBean;
    //private ArrayList<PostagemBean> postagem;
    private ArrayAdapter<PostagemBean> adaptador;
    private Button btRetornaMenu;
    private int adaptadorLayout;
    String id_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_postagens);
        final Bundle bundle = getIntent().getExtras();
        id_string = bundle.getString("id");
        //Toast.makeText(ListarPostagens.this,"id encaminhado na intent : "+id_string, Toast.LENGTH_SHORT).show();
        btRetornaMenu = (Button)findViewById(R.id.botao_retornar);
        ListViewPostagens =  (ListView) findViewById(R.id.listViewPostagens);
        PostagemDAO postagemDAO =  new PostagemDAO(ListarPostagens.this);
        listaPostagensBean = (ArrayList<PostagemBean>) postagemDAO.recuperarRegistros(bundle.getString("id"));
        btRetornaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

                    Intent intent = new Intent(ListarPostagens.this, VisualizarPost_com_Respostas.class);
                    //Parcelable parceable = (Parcelable) ListViewPostagens.getItemAtPosition(position);
                    PostagemBean selecionada = selecionarPostagem(position, listaPostagensBean);
                    String id_enviar = Integer.toString(selecionada.getId());
                    //Intent intent = new Intent(ListarPostagens.this,VisualizarPostagem.class);
                    intent.putExtra("id",id_enviar);
                    intent.putExtra("titulo",selecionada.getTitulo());
                    intent.putExtra("postagem", selecionada.getPostagem());

                  ;
                    //intent.putExtra("postagem", parceable);
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

    public PostagemBean selecionarPostagem(int posicao_fora, ArrayList<PostagemBean> lista_postagem){
        PostagemBean postagemselecionada;
        postagemselecionada = lista_postagem.get(posicao_fora);

        Log.d("Test", "ID da postagem  selecionada : " + postagemselecionada.getId());
        Log.d("Test", "Titulo da postagem  selecionada : " + postagemselecionada.getTitulo());
        Log.d("Test", " texto da postagem selecionada    : "+postagemselecionada.getPostagem());
        return postagemselecionada;
    }




}
