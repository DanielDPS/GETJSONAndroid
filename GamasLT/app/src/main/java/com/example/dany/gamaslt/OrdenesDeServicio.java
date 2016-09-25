package com.example.dany.gamaslt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrdenesDeServicio extends AppCompatActivity implements RespuestaJson {


    ListView lista;
    SwipeRefreshLayout swipeRefreshLayout;
    Adaptador ordeersApadter=null;
    TextView user,Pass;
    Usuario usuario;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String var;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenes_de_servicio);



        lista = (ListView)findViewById(R.id.milista);
        swipeRefreshLayout=  (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary );


       final AlertDialog.Builder builder =new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        navigationView = (NavigationView)findViewById(R.id.menu);
        View header = navigationView.getHeaderView(0);
        user = (TextView)header.findViewById(R.id.txtUsuario);
        Pass = (TextView)header.findViewById(R.id.password);


        usuario = (Usuario)Sesion.getSesion().getUsuario();
        user.setText("Bienvenido: "+usuario.get_Nombre() + " " +usuario.get_Apellidos());
        Pass.setText("Has iniciado sesión como: " + usuario.get_Usuario());




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                drawerLayout.closeDrawers();

                switch (item.getItemId()){

                    case R.id.cuenta:
                        Sesion.getSesion();
                      break;
                    case R.id.cerrar:

                        builder.setTitle("Aviso");
                        builder.setMessage("Está seguro que desea cerrar sesión?");
                        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CerrarSesion();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Sesion.getSesion();

                            }
                        });
                        builder.show();

                        break;
                    case R.id.salir:

                        builder.setTitle("Aviso");
                        builder.setMessage("Está seguro que desea cerrar la aplicación?");
                        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);


                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Sesion.getSesion();
                            }
                        });
                        builder.show();



                        break;

                }
                return  true;
            }
        });



        drawerLayout  = (DrawerLayout)findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                GetAsyncTask();
            }
        });

        lista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int filaSuperior = (

                        lista == null//Si la lista esta vacía ó

                                || lista.getChildCount() == 0) ? 0 : lista.getChildAt(0).getTop();//Estamos en el elemento superior

                swipeRefreshLayout.setEnabled(filaSuperior >= 0); //Activamos o desactivamos el swipe layout segun corresponda


            }
        });


                GetAsyncTask();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("instancia",JsonParser.getInstancia());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        JsonParser nuevainstancia =(JsonParser) savedInstanceState.getSerializable("instancia");
        Toast.makeText(getApplicationContext(),Integer.toString( nuevainstancia.getOrdeers().size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void ProcesoFinalizado(String resultado) {
        //JsonParser.getInstancia();
        JsonParser.parseJson(resultado);
        ordeersApadter = new Adaptador(OrdenesDeServicio.this, JsonParser.getInstancia().getOrdeers());
        lista.setAdapter(ordeersApadter );
        swipeRefreshLayout.setRefreshing(false);
    }
    /*
    @Override
    protected void onPause(){ super.onPause();}
    @Override
    protected void onResume() {
        super.onResume();
    }

*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void CerrarSesion()
    {
        Intent login = new Intent(getApplicationContext(),Login.class);
        Sesion.CloseSession();
        startActivity(login);

    }
    public JsonAsyncTask GetAsyncTask()
    {
        JsonAsyncTask proceso =new JsonAsyncTask(this);
        proceso.execute(ApplicactionUrls.Url);
        return proceso;
    }
}
