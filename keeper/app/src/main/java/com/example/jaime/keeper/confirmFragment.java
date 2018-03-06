package com.example.jaime.keeper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jaime.keeper.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class confirmFragment extends DialogFragment {
    INuevaNotaDialogListener mListener;
    List<Categoria> listaCategorias;
    EditText editTextTitulo, cuerpo;
    Context ctx = getContext();
    Spinner spinnerCategorias;

    public confirmFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);



    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Bundle bundle=getActivity().getIntent().getExtras();

        KeeperService api = ServiceGenerator.createService(KeeperService.class);
        Call<Categoria> categorias = api.listarCategorias(bundle.get("X-API-KEY").toString());
        categorias.enqueue(new Callback<Categoria>() {
            @Override
            public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                if(response.isSuccessful()){
                    listaCategorias = (List<Categoria>) response.body();
                    SpinnerCategoriaAdapter adapter = new SpinnerCategoriaAdapter(ctx,
                            R.layout.spinner_categoria_item, listaCategorias);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerCategorias.setAdapter(adapter);
                    //wtf pasa aqui??
                    spinnerCategorias.setOnItemSelectedListener(this);
                }
            }

            @Override
            public void onFailure(Call<Categoria> call, Throwable t) {

            }
        });
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nuevo usuario")
                .setMessage("Introduce los datos del nuevo usuario")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        String titulo = editTextTitulo.getText().toString();
                        String body = cuerpo.getText().toString();

                        mListener.onGuardarClick(titulo);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        dialog.dismiss();
                    }
                });

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_confifm,null);



        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            mListener = (INuevaNotaDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement INuevoUsuarioDialogListener");
        }

    }
}
