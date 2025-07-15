package com.example.mestradobip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;

public class TelaEntrada extends AppCompatActivity {
    Acessa objA = new Acessa();
    static Bitmap imgUsuario = null;
    TextView txtNome, txtFormacao, txtUnidade, txtMatricula, txtQuantAula, txtPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_entrada);
        ImageView imVEntrada = findViewById(R.id.imVEntrada);
        imVEntrada.setImageBitmap(imgUsuario);
        txtNome= findViewById(R.id.txtNome);
        txtFormacao= findViewById(R.id.txtFormacao);
        txtUnidade= findViewById(R.id.txtUnidade);
        txtMatricula= findViewById(R.id.txtMatricula);
        txtQuantAula= findViewById(R.id.txtQuant_aula);
        txtPontuacao= findViewById(R.id.txtPontuacao);


        setTitle ("UNICID");
        preencher();


    }

    public void preencher(){
        objA.entBanco(this);

            try {
                objA.RS = objA.stmt.executeQuery("Select * from professor WHERE cpf='"+Tela_Login.cpf+"'");
                if (objA.RS.next()){
                    txtNome.setText(txtNome.getText()+" "+objA.RS.getString("nome_prof"));
                    txtFormacao.setText(txtFormacao.getText()+" "+objA.RS.getString("formacao_prof"));
                    txtUnidade.setText(txtUnidade.getText()+" "+objA.RS.getInt("sede_prof")+"");
                    txtMatricula.setText(txtMatricula.getText()+" "+objA.RS.getInt("matricula_prof")+"");
                    txtQuantAula.setText(txtQuantAula.getText()+" "+objA.RS.getInt("quant_aula_prof")+"");
                    txtPontuacao.setText(txtPontuacao.getText()+" "+objA.RS.getInt("pontos_prof")+"");
                    Lista_Aulas.especialidade= objA.RS.getString("area_prof");
                    tela_consulta.especialidadeCon= objA.RS.getString("area_prof");
                    tela_consulta.CPFcons= objA.RS.getString("CPF");
                }
            } catch (SQLException e) {

            }


    }

    public void ampliar(View v){
        Intent trocaTela = new Intent(TelaEntrada.this, Lista_Aulas.class);
        startActivity(trocaTela);
    }
    public void consulta(View v){
        Intent trocaTela = new Intent(TelaEntrada.this, tela_consulta.class);
        startActivity(trocaTela);
    }


}