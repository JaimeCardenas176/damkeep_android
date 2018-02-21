package com.example.jaime.keeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import model.ResponseAuthUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        private EditText nombre;
        private EditText email;
        private EditText pass;
        private Button login;
        private Button register;
        private Call<ResponseAuthUser> peticion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        nombre = findViewById(R.id.nombreUsuario);
        email = findViewById(R.id.emailUsuario);
        pass = findViewById(R.id.passUsuario);
        KeeperService apiMiguel = ServiceGenerator.createService(KeeperService.class);

        peticion = apiMiguel.doLogin(email.getText().toString(), pass.getText().toString());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peticion.enqueue(new Callback<ResponseAuthUser>() {
                    @Override
                    public void onResponse(Call<ResponseAuthUser> call, Response<ResponseAuthUser> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseAuthUser> call, Throwable t) {

                    }
                });
            }
        });
    }

    private boolean checkFields(){
        boolean res = false;

        return res;
    }
}


