package com.example.sqlite2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.sqlite2019.dao.AlumnoDao;
import com.example.sqlite2019.model.Alumno;
import com.example.sqlite2019.util.Mensajes;

public class AlumnoActivity extends AppCompatActivity {
    private EditText edtNombreA, edtCod, edtCorreo;
    private AlumnoDao alumnoDAO;
    private Alumno alumno;
    private int idalum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        alumnoDAO = new AlumnoDao(this);

        edtNombreA = (EditText) findViewById(R.id.txtNombreA);
        edtCod = (EditText) findViewById(R.id.txtCod);
        edtCorreo = (EditText) findViewById(R.id.txtCorreo);

        idalum = getIntent().getIntExtra("ALUMNO_ID",0);
        if(idalum > 0){
            Alumno model = alumnoDAO.buscarAlumnoPorID(idalum);
            edtNombreA.setText(model.getNombres());
            edtCod.setText(model.getCod());
            edtCorreo.setText(model.getCorreo());
            setTitle("Actualizar Alumno");
        }
    }
    @Override
    protected void onDestroy() {
        alumnoDAO.cerrarDB();
        super.onDestroy();
    }
    private void registrar(){
        boolean validar = true;
        String nombre = edtNombreA.getText().toString();
        Integer cod = Integer.parseInt(edtCod.getText().toString());
        String correo = edtCorreo.getText().toString();
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombreA.setError(getString(R.string.Login_validaNombre));
        }
        if(cod == null || cod.equals("")){
            validar = false;
            edtNombreA.setError(getString(R.string.Login_validaAlumno));
        }
        if(correo == null || correo.equals("")){
            validar = false;
            edtNombreA.setError(getString(R.string.Login_validaCorreo));
        }
        if(validar){
            alumno = new Alumno();
            alumno.setNombres(nombre);
            alumno.setCod(cod);
            alumno.setCorreo(correo);
            if(idalum > 0){
                alumno.set_id(idalum);
            }
            long resultado = alumnoDAO.modificarAlumno(alumno);
            if(resultado != -1){
                if(idalum > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_user_modificado));
                    startActivity(new Intent(this, ListAlumnoActivity.class));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_user_guardado));
                    startActivity(new Intent(this, ListAlumnoActivity.class));
                }
                finish();
                //startActivity(new Intent(this, MainActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_user_error));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardarA:
                this.registrar();
                break;
            case R.id.action_menu_sairA :
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
