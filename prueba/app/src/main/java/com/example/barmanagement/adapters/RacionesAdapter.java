package com.example.barmanagement.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barmanagement.R;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RacionesAdapter extends FirestoreRecyclerAdapter<Category, RacionesAdapter.ViewHolder> {

    Context context;
    public List<HashMap<String, Object>>texto;

    private OnCategoryListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RacionesAdapter(@NonNull FirestoreRecyclerOptions<Category> options, Context context) {
        super(options);
        this.context = context;
        texto = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raciones_item,parent,false);

        return new ViewHolder(view, listener);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Category model) {

        holder.txtNombre.setText(model.getNombre());
        holder.txtCantidad.setText(model.getCantidad());
        holder.txtPrecio.setText(model.getPrecio());
    }
    public List<HashMap<String, Object>> getTexto() {
        return texto;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        //componentes del RecyclerView
        TextView txtNombre;
        TextView txtPrecio;
        EditText txtCantidad;

        OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.txtFood);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidadFood);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener( view ->  {
                int position = getBindingAdapterPosition();
                if(position != RecyclerView.NO_POSITION && listener != null){
                    listener.onCategoryClick(getSnapshots().getSnapshot(position), position);
                }

            });
            MyTextWatcher myTextWatcher = new MyTextWatcher(txtCantidad, txtNombre, txtPrecio);
            txtCantidad.addTextChangedListener(myTextWatcher);

        }
    }
    public class MyTextWatcher implements TextWatcher {
        private EditText txtExtra;
        private TextView txtBebida;
        private TextView txtPrecio;


        public MyTextWatcher(EditText txtExtra, TextView txtBebida, TextView txtPrecio) {
            this.txtExtra = txtExtra;
            this.txtBebida = txtBebida;
            this.txtPrecio = txtPrecio;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String cantidad = String.valueOf(txtExtra.getText());
            HashMap<String,Object> map= new HashMap<>();
            map.put(txtBebida.getText().toString(),cantidad);
            map.put("pecio", txtPrecio.getText().toString());
            texto.add(map);


        }
        @Override
        public void afterTextChanged(Editable s) { }
    }

    public  interface OnCategoryListener{
        void onCategoryClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnClickListener(OnCategoryListener listener){
        this.listener = listener;
    }


}
