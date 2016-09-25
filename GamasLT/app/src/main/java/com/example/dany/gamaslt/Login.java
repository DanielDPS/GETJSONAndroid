package com.example.dany.gamaslt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
public class Login extends AppCompatActivity {

    Usuario usuario ;
    Button btnAccesar;
    EditText txtUsuario,txtContrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //<editor-fold desc="Toolbar">
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logoapp48);
        //</editor-fold>
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtContrasena = (EditText)findViewById(R.id.txtPassword);
        btnAccesar = (Button)findViewById(R.id.btnAcceder);


        usuario = new Usuario();
        usuario.set_Usuario("gamas");
        usuario.set_Contra("ddd");
        usuario.set_Nombre("Edgar");
        usuario.set_Apellidos("Ramirez Martinez");

        Sesion.getSesion().AgregarUsuario(usuario);

        usuario = new Usuario();
        usuario.set_Usuario("dany");
        usuario.set_Contra("123");
        usuario.set_Nombre("Daniel");
        usuario.set_Apellidos("Ramirez Martinez");
        Sesion.getSesion().AgregarUsuario(usuario);

        txtUsuario.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        btnAccesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String User =txtUsuario.getText().toString();
                String Pass = txtContrasena.getText().toString();
                Usuario user2;
                Intent intento;
                for(int i =0; i <Sesion.getSesion().getListado().size(); i++) {

                    user2 =(Usuario) Sesion.getSesion().getListado().get(i);

                    if (txtUsuario.getText().toString().equals("")  || txtContrasena.getText().toString().equals("")){

                        txtUsuario.setError("Usuario vacio");
                        txtContrasena.setError("Contraseña vacia");


                    }else {

                        txtUsuario.setError(null);
                        txtContrasena.setError(null);
                        if (User.equals(user2.get_Usuario()) && Pass.equals(user2.get_Contra())) {

                            txtContrasena.requestFocus();
                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


                            intento = new Intent(getApplicationContext(), OrdenesDeServicio.class);
                            Sesion.getSesion().setUsuario(user2);
                            startActivity(intento);
                            //overridePendingTransition(R.animator.zoom_back_in,R.animator.zoom_back_in);


                        } else if (!User.equals(user2.get_Usuario()) || !Pass.equals(user2.get_Contra()) ){

                            txtUsuario.setError("Usuario incorrecto");
                            txtContrasena.setError("Contraseña incorrecta");

                        }

                    }
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            return  false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if ( id==R.id.cerrar)
        {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

public  void OcultarTeclado(EditText myEditText)
{
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
}

}
