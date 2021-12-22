package com.ap.examen_correccion;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdaptador  extends RecyclerView.Adapter<MyAdaptador.ViewHolder> {

    private List<String> divisas;
    private LayoutInflater inflador;

    private TextView ultimaSeleccion;
    private int posicionUltimaSeleccion;

    MyAdaptador(Context contexto,List<String> divisas){
        this.divisas=divisas;
        this.inflador=LayoutInflater.from(contexto);
        posicionUltimaSeleccion = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflador.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }
    //ENLAZAR POR CADA UNO DE LOS ELEMENTOS
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String divisa = divisas.get(position);  //DEVUELVE EL TEXTIEW DEL RECICLER VIEW
        holder.tvDivisa.setText(divisa);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView divisa = view.findViewById(R.id.tvDivisa);
                //DESMARCAR SI YA ESTABA SELECCIONADO
                if(divisa == ultimaSeleccion){
                    posicionUltimaSeleccion=-1;
                    ultimaSeleccion = null;
                    divisa.setBackgroundColor(Color.WHITE);
                } else {
                    //SI HAY OTRO MARCADO DE ANTES ENTONCES LO DESMARCO
                    if(ultimaSeleccion!=null){
                        ultimaSeleccion.setBackgroundColor(Color.WHITE);
                    }
                    divisa.setBackgroundColor(Color.BLUE);
                    posicionUltimaSeleccion = position;
                    ultimaSeleccion = divisa;
                }

            }

        });
    }

    @Override
    public int getItemCount() {
        return divisas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDivisa;
        ViewHolder(View itemView) {
            super(itemView);
            tvDivisa = itemView.findViewById(R.id.tvDivisa);
        }
    }

    public int getElementoSeleccionado(){
        return  this.posicionUltimaSeleccion;
    }
}


