package com.example.calculadoraimc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList resultado;

    CustomAdapter(Context context, ArrayList resultado){
        this.resultado = resultado;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.resultado_txt.setText(String.valueOf(resultado.get(position)));
    }

    @Override
    public int getItemCount() {
        return resultado.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView resultado_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            resultado_txt = itemView.findViewById(R.id.resultado_txt);
        }
    }
}
