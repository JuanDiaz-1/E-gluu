package mx.ita.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Howto5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto5);
    }

    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){

            case R.id.imageButtoncancel5:
                startActivity(new Intent(Howto5.this,MainActivity.class));
                break;

        }


    }

}