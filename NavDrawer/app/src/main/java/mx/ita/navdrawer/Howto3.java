package mx.ita.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Howto3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_howto3);
        getSupportActionBar().hide();
    }


    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){

            case R.id.imageButtonrightarrow3:
                startActivity(new Intent(Howto3.this,Howto4.class));
                break;

            case R.id.imageButtonleftarrow3:
                startActivity(new Intent(Howto3.this,Howto2.class));
                break;

            case R.id.imageButtoncancel3:
                startActivity(new Intent(Howto3.this,MainActivity.class));
                break;

        }
    }
}