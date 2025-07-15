package com.example.mestradobip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Tela_Login extends AppCompatActivity {
    EditText editLogin, editSenha;
    public static String cpf;
    Acessa objA = new Acessa();
    Bitmap bitmap = null;
    byte[] imgBytes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setTitle ("UNICID");


        editLogin = findViewById(R.id.editUsuario);
        editSenha = findViewById(R.id.editSenha);

        TextWatcher criarMascara  = AlciMask.aplicarMascara
                (editLogin, "###.###.###-##");//cria a máscara
        editLogin.addTextChangedListener(criarMascara);



    }

    public void loginBanco(View alci){
        objA.entBanco(this);
        cpf = AlciMask.removerMascara(editLogin.getText().toString()).toString();
        tela_confirmacao.cpf = cpf;

        String senha = editSenha.getText().toString();
        if (cpf.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Entre com usuário e senha", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                objA.RS = objA.stmt.executeQuery("Select * from login WHERE cpf='"+cpf+"' and senha='"+senha+"'");
                if (objA.RS.next()){
                    imgBytes = objA.RS.getBytes("imagem_bd");
                    if (imgBytes != null) {
                        bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                        TelaEntrada.imgUsuario=bitmap;
                    }



                    Intent intensao = new Intent(Tela_Login.this, TelaEntrada.class);
                    startActivity(intensao);
                    finish();
                }else{
                    TextView txtAviso = findViewById(R.id.txtAviso);
                    txtAviso.setText("cpf ou senha não conferem");
                }
            } catch (SQLException e) {

            }
        }
    }
}