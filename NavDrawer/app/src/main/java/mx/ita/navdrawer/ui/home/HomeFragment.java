package mx.ita.navdrawer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import mx.ita.navdrawer.Producto;
import mx.ita.navdrawer.R;

public class HomeFragment extends Fragment {
    EditText codigoproducto,nombreproducto,detallesproducto,stock,preciocompra,precioventa;
    Button botonagregar;
    Button btScan;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private HomeViewModel homeViewModel;
    private Button mBtnAgregar;
    private ListView mListView;
    private EditText mEditText;
    private List<String> mLista = new ArrayList<>();
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        codigoproducto=root.findViewById(R.id.CodigoProducto);
        nombreproducto=root.findViewById(R.id.NombreProducto);
        detallesproducto=root.findViewById(R.id.Detallesproducto);
        stock=root.findViewById(R.id.StockProducto);
        preciocompra=root.findViewById(R.id.PrecioCompra);
        precioventa=root.findViewById(R.id.PrecioVenta);

        btScan = root.findViewById(R.id.bt_Scan);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });

        inicializarfirebase();

        botonagregar=root.findViewById(R.id.buttonAgregar);
        botonagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo=codigoproducto.getText().toString();
                String nombre=nombreproducto.getText().toString();
                String detalles=detallesproducto.getText().toString();
                String stoc=stock.getText().toString();
                String precioc=preciocompra.getText().toString();
                String preciov=precioventa.getText().toString();
                if(codigo.equals("") || nombre.equals("") || detalles.equals("") || stoc.equals("") || precioc.equals("") || preciov.equals("")){
                    validacion();
                }else{
                    Producto p=new Producto();
                    p.setCodigoprodcuto(codigo);
                    p.setNombreproducto(nombre);
                    p.setDetallesproducto(detalles);
                    p.setStock(stoc);
                    p.setPreciocompra(precioc);
                    p.setPrecioventa(preciov);
                    databaseReference.child("Producto").child(p.getCodigoprodcuto()).setValue(p);
                    limpiarcajas();

                }
            }
        });


        return root;



    }
    private void inicializarfirebase(){
      FirebaseApp.initializeApp(getActivity());
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }
    private void validacion() {
        String codigo=codigoproducto.getText().toString();
        String nombre=nombreproducto.getText().toString();
        String detalles=detallesproducto.getText().toString();
        String stoc=stock.getText().toString();
        String precioc=preciocompra.getText().toString();
        String preciov=precioventa.getText().toString();
        if(codigo.equals("")){
            codigoproducto.setError("Required");
        }
        if(nombre.equals("")){
            nombreproducto.setError("Required");
        }
        if(detalles.equals("")){
            detallesproducto.setError("Required");
        }
        if(stoc.equals("")){
            stock.setError("Required");
        }
        if(precioc.equals("")){
            preciocompra.setError("Required");
        }
        if(preciov.equals("")){
            precioventa.setError("Required");
        }
    }
    private void limpiarcajas(){
        codigoproducto.setText("");
        nombreproducto.setText("");
        detallesproducto.setText("");
        stock.setText("");
        preciocompra.setText("");
        precioventa.setText("");
    }
    public void escanear() {

        IntentIntegrator intent = IntentIntegrator.forSupportFragment(HomeFragment.this);
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
                codigoproducto.setText(result.getContents().toString());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}