package com.example.mestradobip;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TabelaAdaptar extends BaseAdapter {
    private Context contexto;
    private ArrayList<DadosTabela> listaDados;
    int cor=0;

    public  TabelaAdaptar
            (Context context, ArrayList<DadosTabela> lista){

        contexto = context;
        listaDados = lista;
    }

    @Override
    public int getCount() {
        return listaDados.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaDados.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }
    @Override
    public View getView
            (int posicao, View converterView, ViewGroup parent) {

        DadosTabela  ItensTabela = listaDados.get(posicao);
        View LayoutModelo;

        if(converterView == null){
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutModelo = inflater.inflate(R.layout.lista_aulas_disponiveis, null);
        }
        else{
            LayoutModelo = converterView;
        }
        TextView txt_nome = LayoutModelo.findViewById(R.id.txtNomeEscola);
        txt_nome.setText(ItensTabela.nome_escola);

        TextView txt_endereco =  LayoutModelo.findViewById(R.id.txtEndEscola);
        txt_endereco.setText(ItensTabela.endereco_escola);

        TextView txt_telefone =  LayoutModelo.findViewById(R.id.txtTelEscola);
        txt_telefone.setText(ItensTabela.telefone_escola);

        TextView txt_curso =  LayoutModelo.findViewById(R.id.txtCursoEscola);
        txt_curso.setText(ItensTabela.nome_Curso);

        TextView txt_disciplina =  LayoutModelo.findViewById(R.id.txtDiscEscola);
        txt_disciplina.setText(ItensTabela.nome_Disciplina);

        TextView txt_numeroAulas =  LayoutModelo.findViewById(R.id.txtNumAulas);
        txt_numeroAulas.setText(ItensTabela.num_Aulas+ " ha");

        TextView txt_status =  LayoutModelo.findViewById(R.id.txtStaEscola);
        txt_status.setText("Aula " + ItensTabela.status+" Cod: " + ItensTabela.id_dispon);
//
        ImageView iv =   LayoutModelo.findViewById(R.id.imvEscola);
        iv.setImageBitmap(ItensTabela.imgEscola);





        return LayoutModelo;

    }


}
