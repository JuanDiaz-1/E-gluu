package mx.ita.navdrawer;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Contacto extends Fragment {
    Button face;
    Button correo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_contacto, container, false);
        face = view.findViewById(R.id.btnFacebook);
        correo = view.findViewById(R.id.btncorreo);
        correo.setOnClickListener(this::onClick);
        face.setOnClickListener(this::onClick);
        return view;
    }
    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){

            case R.id.btnFacebook:
                miIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/E-Gluu-108169505036731"));
                break;
            case R.id.btncorreo:
                miIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/equipo3egluu/"));
                break;

        }
        startActivity(miIntent);
    }
}