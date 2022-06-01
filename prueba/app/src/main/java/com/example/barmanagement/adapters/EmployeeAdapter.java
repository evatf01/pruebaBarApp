package com.example.barmanagement.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.R;
import com.example.barmanagement.models.User;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    List<User> empleados = new ArrayList<>();
    Context context;
    private final RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView
    User user;

    public EmployeeAdapter(List<User> empleados, Context context, RecyclerViewClickListener listener) {
        this.empleados = empleados;
        this.context = context;
        this.listener = listener;

    }

    public void setEmpleado(List<User> users) {
        this.empleados = users;
        Log.d("holaaaaa","holaaaaaaa");
    }

    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtName.setText(empleados.get(position).getName());
        holder.txtDni.setText(empleados.get(position).getDni());

    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        TextView txtName;
        TextView txtDni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtNameUser);
            txtDni = (TextView) itemView.findViewById(R.id.txtDni);
            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {

            listener.onClick(v,getBindingAdapterPosition());
        }
    }

}
