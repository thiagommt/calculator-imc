package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    private EditText Peso;
    private EditText Altura;
    private TextView Resultado;
    private Button Salvar;
    private RecyclerView RecyclerVw;

    MyDatabaseHelper myDB;
    ArrayList<String> resultados;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerVw = findViewById(R.id.recyclerView);
        Peso = findViewById(R.id.edtPeso);
        Altura = findViewById(R.id.edtAltura);
        Resultado = findViewById(R.id.txtResultado);
        Salvar = findViewById(R.id.btnSalvar);
        Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.addResultados(Resultado.getText().toString());
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        resultados = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, resultados);
        RecyclerVw.setAdapter(customAdapter);
        RecyclerVw.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    public void CalculaIMC(View view){
        double peso = Double.parseDouble(Peso.getText().toString());
        double altura = Double.parseDouble(Altura.getText().toString());
        double resultado = peso/(altura*altura);

        if (resultado >= 18.5 && resultado <= 24.99) {
            Resultado.setText("Peso Normal | IMC = "+resultado);
        } else if (resultado >= 25 && resultado <= 29.99) {
            Resultado.setText("Acima Peso | IMC = "+resultado);
        } else if (resultado >= 30 && resultado <= 34.99) {
            Resultado.setText("Obesidade I | IMC = "+resultado);
        } else if (resultado >= 35 && resultado <= 39.99) {
            Resultado.setText("Obesidade II | IMC = "+resultado);
        } else if (resultado > 40) {
            Resultado.setText("Obesidade III | IMC = "+resultado);
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Sem dados.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                resultados.add(cursor.getString(1));
            }
        }
    }
}