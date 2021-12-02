package mx.ita.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import android.view.View;

public class Howto extends AppCompatActivity {

    ImageView rightarrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_howto);
        getSupportActionBar().hide();







    }
    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){

            case R.id.imageButtonrightarrow1:
                startActivity(new Intent(Howto.this,Howto2.class));
                break;

            case R.id.imageButtoncancel1:
                startActivity(new Intent(Howto.this,MainActivity.class));
                break;
        }
    }


}