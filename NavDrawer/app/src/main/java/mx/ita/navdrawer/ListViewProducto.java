package mx.ita.navdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mx.ita.navdrawer.model.Producto;

public class ListViewProducto extends Activity {
    private List<Producto> listproducto=new ArrayList<Producto>();
    ArrayAdapter<Producto> arrayAdapterProdcuto;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    Producto productoseleccionado;
    EditText n,d,s,p1,p2;
    String v1,v2,v3,v4,v5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView=findViewById(R.id.listviewproducto);
        inicializarfirebase();
        listaDatos();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productoseleccionado=(Producto) parent.getItemAtPosition(position);
                n.setText(productoseleccionado.getNombreproducto());
                d.setText(productoseleccionado.getDetallesproducto());
                s.setText(productoseleccionado.getStock());
                p1.setText(productoseleccionado.getPreciocompra());
                p2.setText(productoseleccionado.getPrecioventa());
                v1=n.toString().trim();
                v2=d.toString().trim();
                v3=s.toString().trim();
                v4=p1.toString().trim();
                v5=p2.toString().trim();
                Intent intent=new Intent(ListViewProducto.this,ViewShowProduct.class);
                intent.putExtra("NomP",v1);
                intent.putExtra("DetP",v2);
                intent.putExtra("StoP",v3);
                intent.putExtra("PrecP",v4);
                intent.putExtra("PrevP",v5);
                startActivity(intent);
            }
        });
    }

    private void listaDatos() {
        databaseReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listproducto.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                Producto p=objSnapshot.getValue(Producto.class);
                listproducto.add(p);
                arrayAdapterProdcuto =new ArrayAdapter<Producto>(ListViewProducto.this, android.R.layout.simple_list_item_1,listproducto);
                listView.setAdapter(arrayAdapterProdcuto);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarfirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }
}
