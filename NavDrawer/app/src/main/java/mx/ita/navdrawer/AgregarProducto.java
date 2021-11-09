package mx.ita.navdrawer;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.ita.navdrawer.model.Producto;

public class AgregarProducto extends Activity {
    EditText codigoproducto,nombreproducto,detallesproducto,stock,preciocompra,precioventa;
    Button botonagregar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        codigoproducto=findViewById(R.id.CodigoProducto);
        nombreproducto=findViewById(R.id.NombreProducto);
        detallesproducto=findViewById(R.id.Detallesproducto);
        stock=findViewById(R.id.StockProducto);
        preciocompra=findViewById(R.id.PrecioCompra);
        precioventa=findViewById(R.id.PrecioVenta);
        inicializarfirebase();

        botonagregar=findViewById(R.id.buttonAgregar);
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

    }
    private void inicializarfirebase(){
        FirebaseApp.initializeApp(this);
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
}
