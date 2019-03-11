package nredondo26.com.fastjob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button login, registro;
    EditText email, pass;
    String EmailHolder, PasswordHolder;
    Boolean CheckEditText;
    String HttpUrl = "http://192.168.2.101/fastjob/public/loginandroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.blogin);
        registro = findViewById(R.id.bregistro);
        email = findViewById(R.id.editemail);
        pass = findViewById(R.id.edinit);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if (CheckEditText) {
                    UserLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "Por favor complete todos los campos del formulario.", Toast.LENGTH_LONG).show();
                }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Escoger_perfil_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void UserLogin() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onResponse(String ServerResponse) {

                        if (ServerResponse.contains("true")) {
                            String[] parts = ServerResponse.split("-");
                            String tipo_usuario = parts[1];
                            String id_usaurio= parts[2];

                            if(tipo_usuario.equals("usuario")){
                                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                                intent.putExtra("id_usuario", id_usaurio);
                                startActivity(intent);
                            }else{

                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario o Password errados", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, "Problemas con la conexión", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("correo", EmailHolder);
                params.put("password", PasswordHolder);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

    public void CheckEditTextIsEmptyOrNot() {
        EmailHolder = email.getText().toString().trim();
        PasswordHolder = pass.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(EmailHolder) && !TextUtils.isEmpty(PasswordHolder);
    }
}
