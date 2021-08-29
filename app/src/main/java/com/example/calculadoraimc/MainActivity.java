package com.example.calculadoraimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    private EditText Peso;
    private EditText Altura;
    private TextView Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Peso = findViewById(R.id.edtPeso);
        Altura = findViewById(R.id.edtAltura);
        Resultado = findViewById(R.id.txtResultado);
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
}