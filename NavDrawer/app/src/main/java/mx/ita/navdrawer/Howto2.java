package mx.ita.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Howto2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_howto2);
        getSupportActionBar().hide();

    }

    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){

            case R.id.imageButtonrightarrow2:
                startActivity(new Intent(Howto2.this,Howto3.class));
                break;

            case R.id.imageButtonleftarrow2:
                startActivity(new Intent(Howto2.this,Howto.class));
                break;

            case R.id.imageButtoncancel2:
                startActivity(new Intent(Howto2.this,MainActivity.class));
                break;

        }
    }

}