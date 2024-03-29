package com.example.barmanagement.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.barmanagement.R;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.barmanagement.models.Category;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;


public class DrinksAdapter extends FirestoreRecyclerAdapter<Category, DrinksAdapter.ViewHolder> {
    public List<HashMap<String, Object>>texto;
    Context context;
    // interfaz que me he creado para poder hacer onClick en el recyclerView

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DrinksAdapter(@NonNull FirestoreRecyclerOptions<Category> options, Context context) {
        super(options);
        this.context = context;
        texto = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_item,parent,false);

        return new ViewHolder(view);
    }

    public List<HashMap<String, Object>> getTexto() {
        return texto;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Category model) {
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(model.getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgDrink);
        holder.txtBebida.setText(model.getNombre());
        holder.txtCantidad.setText(model.getCantidad());
        holder.txtStock.setText(model.getStock());
        holder.txtPrecio.setText(model.getPrecio());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        // componentes del RecyclerView

        ImageView imgDrink;
        public TextView txtBebida;
        public TextView txtStock;
        public TextView txtPrecio;
        public EditText txtCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = (ImageView) itemView.findViewById(R.id.imgDrink);
            txtBebida = (TextView) itemView.findViewById(R.id.txtBebida);
            txtStock = (TextView) itemView.findViewById(R.id.txtStock);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidad);
            MyTextWatcher myTextWatcher = new MyTextWatcher(txtCantidad, txtBebida, txtStock, txtPrecio);
            txtCantidad.addTextChangedListener(myTextWatcher);

        }
    }

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;
        private TextView txtBebida;
        private TextView txtStock;
        private TextView txtPrecio;

        public MyTextWatcher(EditText editText, TextView txtBebida, TextView txtStock, TextView txtPrecio) {
            this.editText = editText;
            this.txtBebida = txtBebida;
            this.txtStock = txtStock;
            this.txtPrecio = txtPrecio;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String cantidad = editText.getText().toString();
            HashMap<String,Object> map= new HashMap<>();
            map.put(txtBebida.getText().toString(),cantidad);
            map.put("stock", txtStock.getText().toString());
            map.put("precio", txtPrecio.getText().toString());
            texto.add(map);


        }
        @Override
        public void afterTextChanged(Editable s) { }
    }
}
