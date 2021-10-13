package mx.ita.navdrawer;

import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View view){
        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){
            case R.id.btnRegistro:
                miIntent=new Intent(Register.this,MainActivity.class);
                break;
            case R.id.loginText:
                miIntent=new Intent(Register.this,Login.class);
                break;

        }
        startActivity(miIntent);
    }

}
