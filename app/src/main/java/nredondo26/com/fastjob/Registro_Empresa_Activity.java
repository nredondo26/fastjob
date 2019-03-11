package nredondo26.com.fastjob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Registro_Empresa_Activity extends AppCompatActivity {

    TextView nombre,nit,telefono,correo,direccion,economica,password;
    Button registar;
    String hnombre,hnit,htelefono,hcorreo,hdireccion,heconomica,hpassword;
    Boolean CheckEditText;
    String HttpUrl = "http://192.168.2.101/fastjob/public/regitro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__empresa_);

        nombre= findViewById(R.id.editnombre);
        nit= findViewById(R.id.edinit);
        telefono= findViewById(R.id.edittelefono);
        correo= findViewById(R.id.editcorreo);
        direccion= findViewById(R.id.editdireccion);
        economica= findViewById(R.id.editeconomica);
        password= findViewById(R.id.editpassword);
        registar= findViewById(R.id.bregistrar);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText) {
                    registrar_empresa();
                } else {
                    Toast.makeText(Registro_Empresa_Activity.this, "Por favor complete todos los campos del formulario.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void registrar_empresa(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onResponse(String ServerResponse) {

                        if (ServerResponse.contains("true")) {
                            Toast.makeText(Registro_Empresa_Activity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Registro_Empresa_Activity.this,LoginActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Registro_Empresa_Activity.this, "Usuario o Password errados", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Registro_Empresa_Activity.this, "Problemas con la conexión", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tipo_usuario", String.valueOf(1));
                params.put("nombre", hnombre);
                params.put("nit", hnit);
                params.put("telefono", htelefono);
                params.put("correo", hcorreo);
                params.put("direccion", hdireccion);
                params.put("act_economica", heconomica);
                params.put("password", hpassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registro_Empresa_Activity.this);
        requestQueue.add(stringRequest);
    }


    public void CheckEditTextIsEmptyOrNot() {
        hnombre = nombre.getText().toString().trim();
        hnit = nit.getText().toString().trim();
        htelefono = telefono.getText().toString().trim();
        hcorreo = correo.getText().toString().trim();
        hdireccion = direccion.getText().toString().trim();
        heconomica = economica.getText().toString().trim();
        hpassword = password.getText().toString().trim();

        CheckEditText = !TextUtils.isEmpty(hnombre) && !TextUtils.isEmpty(hnit) && !TextUtils.isEmpty(htelefono) && !TextUtils.isEmpty(hcorreo) && !TextUtils.isEmpty(hdireccion) && !TextUtils.isEmpty(heconomica) && !TextUtils.isEmpty(hpassword);
    }
}
