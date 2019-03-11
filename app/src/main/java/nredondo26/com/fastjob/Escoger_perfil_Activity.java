package nredondo26.com.fastjob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Escoger_perfil_Activity extends AppCompatActivity {

    ImageButton candidato,empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_perfil);

        candidato= findViewById(R.id.bcandidato);
        empresa= findViewById(R.id.bempresa);

        candidato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Escoger_perfil_Activity.this, Registro_Candidato_Activity.class);
                startActivity(intent);
            }
        });

        empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Escoger_perfil_Activity.this,Registro_Empresa_Activity.class);
                startActivity(intent);
            }
        });



    }
}
