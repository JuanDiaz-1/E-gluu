package mx.ita.navdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import mx.ita.navdrawer.ui.home.HomeFragment;

public class nav_detalles extends Fragment {
    TextView text;
    EditText codigo,edn,edd,eds,edpc,edpv;
    Button btn,botonupdate,btscaner;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Producto productoencontrado;
    String id;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nav_detalles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_detalles.
     */
    // TODO: Rename and change types and number of parameters
    public static nav_detalles newInstance(String param1, String param2) {
        nav_detalles fragment = new nav_detalles();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_nav_detalles, container, false);
        codigo=root.findViewById(R.id.buscarcodigo);
        btn=root.findViewById(R.id.butonbuscar);
        text=root.findViewById(R.id.Datos);
        inicializarfirebase();
        edn=root.findViewById(R.id.editnombreproducto);
        edd=root.findViewById(R.id.editdetallesproducto);
        eds=root.findViewById(R.id.editstock);
        edpc=root.findViewById(R.id.editdpreciocompra);
        edpv=root.findViewById(R.id.editdprecioventa);
        btscaner=root.findViewById(R.id.bt_Scan);
        btscaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });


        botonupdate=root.findViewById(R.id.btnupdate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id=codigo.getText().toString();
                        for(DataSnapshot objSnapshot : snapshot.getChildren()) {
                            Producto p = objSnapshot.getValue(Producto.class);
                            String idn=objSnapshot.child("codigoprodcuto").getValue(String.class);
                            if(id.equals(idn)){

                                edn.setText(p.getNombreproducto());
                                edd.setText(p.getDetallesproducto());
                                eds.setText(p.getStock());
                                edpc.setText(p.getPreciocompra());
                                edpv.setText(p.getPrecioventa());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        botonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre=edn.getText().toString();
                String detalles=edd.getText().toString();
                String stoc=eds.getText().toString();
                String precioc=edpc.getText().toString();
                String preciov=edpv.getText().toString();
                if(nombre.equals("") || detalles.equals("") || stoc.equals("") || precioc.equals("") || preciov.equals("")){
                    validacion();
                }else{
                    String id=codigo.getText().toString();
                    Producto p=new Producto();
                    p.setCodigoprodcuto(id);
                    p.setNombreproducto(nombre);
                    p.setDetallesproducto(detalles);
                    p.setStock(stoc);
                    p.setPreciocompra(precioc);
                    p.setPrecioventa(preciov);
                    databaseReference.child("Producto").child(p.getCodigoprodcuto()).setValue(p);


                }
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

    private void validacion() {
        String nombre=edn.getText().toString();
        String detalles=edd.getText().toString();
        String stoc=eds.getText().toString();
        String precioc=edpc.getText().toString();
        String preciov=edpv.getText().toString();
        if(nombre.equals("")){
            edn.setError("Required");
        }
        if(detalles.equals("")){
            edd.setError("Required");
        }
        if(stoc.equals("")){
            eds.setError("Required");
        }
        if(precioc.equals("")){
            edpc.setError("Required");
        }
        if(preciov.equals("")){
            edpv.setError("Required");
        }
    }
    private void limpiarcajas(){
        codigo.setText("");
        edn.setText("");
        edd.setText("");
        eds.setText("");
        edpc.setText("");
        edpv.setText("");
    }
    public void escanear() {

        IntentIntegrator intent = IntentIntegrator.forSupportFragment(nav_detalles.this);
        //IntentIntegrator intent = new IntentIntegrator(getActivity());
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("ESCANEAR CODIGO");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getContext(), "Cancelaste el escaneo", Toast.LENGTH_SHORT).show();
            } else {
                codigo.setText(result.getContents().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    }

