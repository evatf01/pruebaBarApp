package com.example.barmanagement.adapters;

<<<<<<< HEAD
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.barmanagement.R;
import java.util.HashMap;
=======
>>>>>>> f60982bb140a9f00c4f7cfaae108c68d11e7d56c
import android.content.ContentValues;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
        import com.bumptech.glide.request.RequestOptions;
        import com.example.barmanagement.R;
        import com.example.barmanagement.models.Category;
        import com.example.barmanagement.models.Comanda;
        import com.google.firebase.firestore.FirebaseFirestore;

<<<<<<< HEAD
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.List;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
>>>>>>> f60982bb140a9f00c4f7cfaae108c68d11e7d56c

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    List<Category> categorias;
    public List<HashMap<String, Object>>texto;
    Context context;
    private final RecyclerViewClickListener listener; // interfaz que me he creado para poder hacer onClick en el recyclerView
    FirebaseFirestore db;

    public DrinksAdapter(List<Category> categorias, Context context, RecyclerViewClickListener listener) {
        this.categorias = categorias;
        this.context = context;
        this.listener = listener;

        texto =new ArrayList<>();
        Log.d("longitud lista", String.valueOf(categorias.size()));
    }


    public  interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drinks_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Glide.with(context). // inserto la foto en el recycler con Glide
                load(categorias.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(holder.imgDrink);
        holder.txtBebida.setText(categorias.get(position).getNombre());
        //holder.txtCantidad.setText(texto.get(position));
    }

    public List<HashMap<String, Object>> getTexto() {
        return texto;
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // componentes del RecyclerView

        ImageView imgDrink;
        public TextView txtBebida;
        public EditText txtCantidad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = (ImageView) itemView.findViewById(R.id.imgDrink);
            txtBebida = (TextView) itemView.findViewById(R.id.txtBebida);
            txtCantidad = (EditText) itemView.findViewById(R.id.txtCantidad);
            MyTextWatcher myTextWatcher = new MyTextWatcher(txtCantidad, txtBebida);
            txtCantidad.addTextChangedListener(myTextWatcher);
            itemView.setOnClickListener(this);

        }
        // implementamos el metodo onClick, donde usaremos el metodo de la interfaz creada anteriormente
        @Override
        public void onClick(View v) {
            listener.onClick(v,getBindingAdapterPosition());
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;
        private TextView txtBebida;

        public MyTextWatcher(EditText editText, TextView txtBebida) {
            this.editText = editText;
            this.txtBebida = txtBebida;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String cantidad = String.valueOf(editText.getText());
            HashMap<String,Object> map= new HashMap<>();
            map.put(txtBebida.getText().toString(),cantidad);
            texto.add(map);


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
