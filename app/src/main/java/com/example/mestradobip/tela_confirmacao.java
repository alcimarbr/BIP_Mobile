package com.example.mestradobip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class tela_confirmacao extends AppCompatActivity {

    public static String nome_escola, endereco_escola, telefone_escola;
    public static int id;
    public static String cpf, acao;
    static Bitmap imgEscola = null;
    Acessa objA = new Acessa();
    DadosTabela dadosConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_confirmacao);

        TextView txtEnd = findViewById(R.id.txtEnderConfir);
        TextView txtNom = findViewById(R.id.txtNomeConf);
        TextView txtTel = findViewById(R.id.txtTelConfirm);
        ImageView imvEsc = findViewById(R.id.imVEscolaConfirm);
        Button botaoAcao = findViewById(R.id.btn4);
        txtNom.setText(nome_escola);
        txtEnd.setText(endereco_escola);
        txtTel.setText(telefone_escola);
        imvEsc.setImageBitmap(imgEscola);
        botaoAcao.setText(acao);

    }

    public void voltar(View alci){
        Intent troca;
        if (acao.equalsIgnoreCase("Confirmar Inscrição")){
            troca =  new Intent (tela_confirmacao.this, Lista_Aulas.class);
        }else{
            troca =  new Intent (tela_confirmacao.this, tela_consulta.class);
        }
        startActivity(troca);
        finish();
        Toast.makeText(this, "Inscrição Cancelada", Toast.LENGTH_SHORT).show();
    }

    public void inserirConfirm(View alci){
        objA.entBanco(this);
        if (acao.equalsIgnoreCase("Confirmar Inscrição")){
            try {
                int res = objA.stmt.executeUpdate("INSERT INTO aula_ampliacao (id_disponiveis,nome_disciplina, unidade, nome_curso, CPF, num_aulas, dia_semana, horario_aula, status_aula, formacao_prof)" +
                        "(SELECT "+id+",nome_disciplina, unidade, nome_curso, '"+cpf+"', num_aulas, dia_semana, horario_aula, status_aula, formacao_prof FROM aulas_disponiveis WHERE id ="+id+") ");
                if (res != 0) {
                    Toast.makeText(this, "Inscrição Realizada", Toast.LENGTH_SHORT).show();
                    Intent trocaTela = new Intent(tela_confirmacao.this, TelaEntrada.class);
                    startActivity(trocaTela);
                }

            } catch (SQLException e) {
                Toast.makeText(this, "Erro na inserção", Toast.LENGTH_SHORT).show();
            }

        }else{
            try {
                int res = objA.stmt.executeUpdate("DELETE FROM aula_ampliacao WHERE id_disponiveis = "+id+"");
                if (res != 0) {
                    Toast.makeText(this, "Inscrição Cancelada", Toast.LENGTH_SHORT).show();
                    Intent trocaTela = new Intent(tela_confirmacao.this, TelaEntrada.class);
                    startActivity(trocaTela);
                }

            } catch (SQLException e) {
                Toast.makeText(this, "Erro na inserção", Toast.LENGTH_SHORT).show();
            }

        }

    }//fim do método

}