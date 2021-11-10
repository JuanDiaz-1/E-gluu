package mx.ita.navdrawer.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import mx.ita.navdrawer.Producto;
import mx.ita.navdrawer.R;

public class SlideshowFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edtce;
    Button btneliminar;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        edtce=root.findViewById(R.id.edce);
        btneliminar=root.findViewById(R.id.btnep);
        inicializarfirebase();
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id=edtce.getText().toString();
                        for(DataSnapshot objSnapshot : snapshot.getChildren()) {
                            Producto p = objSnapshot.getValue(Producto.class);
                            String idn = objSnapshot.child("codigoprodcuto").getValue(String.class);
                            if (id.equals(idn)) {
                                databaseReference.child("Producto").child(p.getCodigoprodcuto()).removeValue();
                                Toast.makeText(getContext(),"Eliminado",Toast.LENGTH_LONG).show();
                            }
                            else{
                                //Toast.makeText(getContext(), "No encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }) ;
            }
        });
        return root;
    }
    private void inicializarfirebase(){
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }
}