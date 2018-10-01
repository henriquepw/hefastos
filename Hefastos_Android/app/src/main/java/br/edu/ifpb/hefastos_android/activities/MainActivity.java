package br.edu.ifpb.hefastos_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.hefastos_android.R;
import br.edu.ifpb.hefastos_android.fragments.ListAssuntoFragment;
import br.edu.ifpb.hefastos_android.fragments.ListDisciplinasFragment;
import br.edu.ifpb.hefastos_android.conection.ConnectionServer;
import br.edu.ifpb.hefastos_android.activities.add.AdicionarActivity;
import br.edu.ifpb.hefastos_android.activities.delete.DeleteActivity;
import br.edu.ifpb.hefastos_android.entities.Disciplina;
import br.edu.ifpb.hefastos_android.fragments.NoItemFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private List<Disciplina> disciplinas = new ArrayList<>();
    public ListView listView;

    private Intent intent;

    private FragmentTransaction fragmentTransaction;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragment = new ListDisciplinasFragment(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void assuntofragmet(int id, String disciplina){
        Fragment fragment = new ListAssuntoFragment(this, id, disciplina);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void noItemfragmet(){
        Fragment fragment = new NoItemFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.action_add:
                intent = new Intent(this, AdicionarActivity.class);
                startActivity(intent);
                break;
            case R.id.action_delete:
                intent = new Intent(this, DeleteActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_list_disciplinas:
                fragment = new ListDisciplinasFragment(this);
                break;
        }

        if(fragment != null){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listarDisciplinas() {

        Call<List<Disciplina>> call = ConnectionServer.getInstance().getService().getAllDisciplina();

        call.enqueue(new Callback<List<Disciplina>>() {
            @Override
            public void onResponse(Call<List<Disciplina>> call, Response<List<Disciplina>> response) {
                try {
                    disciplinas = new ArrayList<>();

                    List<Disciplina> responseDisc = response.body();
                    disciplinas.addAll(responseDisc);
                    Log.i("Disciplians", disciplinas.toString());

                    // setAdapter(disciplinas);
                } catch (Exception e) {
                    Log.e("onResponse", "erro");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Disciplina>> call, Throwable t) {
                Log.e("onFailure", "erro");
            }
        });


    }

    public void setAdapter(List<Disciplina> d) {
        Disciplina disciplina = new Disciplina("disciplina");
        d.add(disciplina);

        Log.i("Disciplians", disciplinas.toString());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, d);
        listView.setAdapter(adapter);
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }
}
