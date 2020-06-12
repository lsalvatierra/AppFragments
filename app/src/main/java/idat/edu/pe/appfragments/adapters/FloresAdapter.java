package idat.edu.pe.appfragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import idat.edu.pe.appfragments.R;
import idat.edu.pe.appfragments.model.Flores;

public class FloresAdapter extends RecyclerView.Adapter<FloresAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Flores> lista;

    public FloresAdapter(Context context) {
        this.context = context;
        this.lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public FloresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_flor, parent, false);
        return new ViewHolder(view);
    }

    public void agregarelementos(ArrayList<Flores> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FloresAdapter.ViewHolder holder, int position) {
        Flores item = lista.get(position);
        //Utilizando la librería Picasso
        Picasso.get().load(item.getUrlImagen()).into(holder.ivimagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivimagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivimagen = itemView.findViewById(R.id.ivimagen);

        }
    }
}
