package com.example.jaime.keeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaime.keeper.model.ResponseAuthUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
        private EditText nombre;
        private EditText email;
        private EditText pass;
        private Button login;
        private Button register;
        private Call<ResponseAuthUser> peticionLogin;
        private Call<ResponseAuthUser> peticionRegistro;
        KeeperService apiMiguel = ServiceGenerator.createService(KeeperService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        nombre = findViewById(R.id.nombreUsuario);
        email = findViewById(R.id.emailUsuario);
        pass = findViewById(R.id.passUsuario);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkFieldsLogin()) {
                    peticionLogin = apiMiguel.doLogin(email.getText().toString(), pass.getText().toString());
                }
                peticionLogin.enqueue(new Callback<ResponseAuthUser>() {
                    @Override
                    public void onResponse(Call<ResponseAuthUser> call, Response<ResponseAuthUser> response) {
                        if(response.isSuccessful()){
                            ResponseAuthUser user = response.body();
                            Intent i = new Intent(MainActivity.this, MenuActivity.class);
                            i.putExtra("emailUsuario", email.getText().toString());
                            i.putExtra("passUsuario", pass.getText().toString());
                            i.putExtra("X-API-KEY", user.getKey());
                            startActivity(i);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAuthUser> call, Throwable t) {

                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFieldsRegister()){
                    peticionRegistro = apiMiguel.doRegister(nombre.getText().toString(), email.getText().toString(), pass.getText().toString());
                }
                peticionRegistro.enqueue(new Callback<ResponseAuthUser>() {
                    @Override
                    public void onResponse(Call<ResponseAuthUser> call, Response<ResponseAuthUser> response) {
                        if(response.isSuccessful()){
                            ResponseAuthUser user = response.body();
                            Intent i = new Intent(MainActivity.this, MenuActivity.class);//TODO pasarle la actividad a la que quiero ir
                            i.putExtra("nombreUsuario", nombre.getText().toString());
                            i.putExtra("emailUsuario", email.getText().toString());
                            i.putExtra("passUsuario", pass.getText().toString());
                            i.putExtra("X-API-KEY", user.getKey());
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAuthUser> call, Throwable t) {

                    }
                });
            }
        });
    }

    private boolean checkFieldsRegister(){
        boolean res;

        if (nombre.getText().toString().length() == 0 || nombre.getText().toString() == " ") {
            nombre.setError("introduzca nombre");
            res = false;
        }
        res = checkFieldsLogin();

        return res;
    }

    private boolean checkFieldsLogin(){
        boolean res= true;
        if (email.getText().toString().trim().length() == 0) {
            email.setError("introduzca email");
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            res=false;
        }if (pass.getText().toString().trim().length() == 0 || pass.getText().toString() == " ") {
            pass.setError("introduzca pass");
            res=false;
        }
    return res;
}
}


