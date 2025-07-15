package com.example.mestradobip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class tela_consulta extends AppCompatActivity {
    Acessa objA= new Acessa();
    Bitmap bitmap = null;
    byte[] imgBytes;
    ListView listaConsul;
    static String especialidadeCon;
    static String CPFcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        listaConsul= findViewById(R.id.lis_consulta);
        preencher();

        //=======
        listaConsul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View alci, int posicao, long id) {
                // Ação ao clicar em um item
                TextView nome = alci.findViewById(R.id.txtNomeEscola);
                TextView ende = alci.findViewById(R.id.txtEndEscola);
                TextView telefone = alci.findViewById(R.id.txtTelEscola);
                TextView status = alci.findViewById(R.id.txtStaEscola);
                ImageView img =  alci.findViewById(R.id.imvEscola);
                tela_confirmacao.nome_escola = nome.getText().toString();
                tela_confirmacao.endereco_escola = ende.getText().toString();
                tela_confirmacao.telefone_escola = telefone.getText().toString()+"";
                tela_confirmacao.acao = "Cancelar Inscrição";

                String[] id_conf = status.getText().toString().split(" ");
                int id_fim = Integer.parseInt(id_conf[3]);
                tela_confirmacao.id = id_fim;

                Drawable drawable = img.getDrawable();

                if (drawable instanceof BitmapDrawable) {
                    // Obter o Bitmap do Drawable
                    bitmap = ((BitmapDrawable) drawable).getBitmap();

                    // Converter o Bitmap em byte[]
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    imgBytes = byteArrayOutputStream.toByteArray();

                    // Agora você tem a imagem como byte[]
                }


                if (imgBytes != null) {
                    bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                    tela_confirmacao.imgEscola=bitmap;
                }
                Intent troca = new Intent(tela_consulta.this, tela_confirmacao.class);
                startActivity(troca);
                finish();
            }
        });
        //=======



    }

    public void preencher() {
        objA.entBanco(this);
        ArrayList<DadosTabela> Agenda = new ArrayList<DadosTabela>();

        try {
            objA.RS = objA.stmt.executeQuery("SELECT ue.nome_unidade, ue.endereco_unidade, ue.telefone_unidade, aula_ampliacao.id_disponiveis, aula_ampliacao.nome_curso, aula_ampliacao.nome_disciplina, aula_ampliacao.num_aulas, aula_ampliacao.status_aula, ue.imagem_unidade\n" +
                    "FROM ue JOIN  aula_ampliacao ON  ue.unidade = aula_ampliacao.unidade WHERE aula_ampliacao.formacao_prof = '"+especialidadeCon+"' and aula_ampliacao.CPF='"+CPFcons+"'");

            while (objA.RS.next()) {
                DadosTabela dados = new DadosTabela();
                dados.id_dispon = objA.RS.getInt("id_disponiveis");
                dados.nome_escola = objA.RS.getString("nome_unidade");
                dados.endereco_escola = objA.RS.getString("endereco_unidade");
                dados.telefone_escola = objA.RS.getString("telefone_unidade");
                dados.nome_Curso = objA.RS.getString("nome_curso");
                dados.nome_Disciplina= objA.RS.getString("nome_disciplina");
                dados.num_Aulas= objA.RS.getInt("num_aulas")+"";
                dados.status= objA.RS.getString("status_aula");

                imgBytes = objA.RS.getBytes("imagem_unidade");
                if (imgBytes != null) {
                    dados.imgEscola = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                }

                Agenda.add(dados);


            }

            listaConsul.setAdapter(new TabelaAdaptar(this, Agenda));

        } catch (SQLException e) {

        }
    }

    public void voltar(View alci){
        Intent troca = new Intent (tela_consulta.this, TelaEntrada.class);
        startActivity(troca);
        finish();

    }
}