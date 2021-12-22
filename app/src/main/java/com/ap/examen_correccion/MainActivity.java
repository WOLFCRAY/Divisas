package com.ap.examen_correccion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> divisas;
    private ArrayList<Double> factoresCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaDivisas();
        inicializaFactoresCambio();

        RecyclerView rvDivisas = findViewById(R.id.rvDivisas);
        LinearLayoutManager managerLayout = new LinearLayoutManager(this);
        rvDivisas.setLayoutManager(managerLayout);
        MyAdaptador adaptador = new MyAdaptador(this, divisas);
        rvDivisas.setAdapter(adaptador);

        DividerItemDecoration decorador = new DividerItemDecoration(rvDivisas.getContext(), managerLayout.getOrientation());
        rvDivisas.addItemDecoration(decorador);

        Switch swVIP = (Switch) findViewById(R.id.swVIP);
        swVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LLAMAR AL METODO QUE HACE EL CAMBIO
                actualizarCambio(swVIP.isChecked(), adaptador.getElementoSeleccionado());
            }
        });

        Button btConevrtir = (Button) findViewById(R.id.bconvertir);
        btConevrtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //LLAMAR AL METODO QUE HACE EL CAMBIO
                actualizarCambio(swVIP.isChecked(), adaptador.getElementoSeleccionado());
            }
        });

    }
    public  void actualizarCambio(boolean VIP,int cambioSeleccionado){
        TextView tvResultado = findViewById(R.id.tvResultado);
        if(cambioSeleccionado != -1){
            try {
                EditText etCantidad = (EditText) findViewById(R.id.cantIni);
                double euros = Double.parseDouble(etCantidad.getText().toString());
                //OPERADOR TERNARIO
                double cambio = VIP? factoresCambio.get(cambioSeleccionado):factoresCambio.get(cambioSeleccionado) * 1.01;
                tvResultado.setText(""+(cambio*euros));
            } catch (NumberFormatException ex){
                tvResultado.setText("");

            }
        } else {
            tvResultado.setText("");
        }
    }

    private void inicializaDivisas(){
        factoresCambio = new ArrayList<Double>();
        factoresCambio.add(1.129);
        factoresCambio.add(0.8544);
        factoresCambio.add(1.4339);
        factoresCambio.add(1.57);
        factoresCambio.add(128.17);
        factoresCambio.add(85.3699);
        factoresCambio.add(1.044);
        factoresCambio.add(18.0304);
        factoresCambio.add(15.2222);
    }

    private void inicializaFactoresCambio(){
        divisas = new ArrayList<>();
        divisas.add("USD");
        divisas.add("GBP");
        divisas.add("CAD");
        divisas.add("JPY");
        divisas.add("INR");
        divisas.add("NZD");
        divisas.add("CHF");
        divisas.add("ZAR");
        divisas.add("RUB");
    }

}