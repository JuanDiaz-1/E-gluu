package mx.ita.navdrawer;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;


public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_NavDrawer);
        setContentView(R.layout.activity_login);

    }



    public void onClick(View view){

        Intent miIntent=null;
        Intent intent = new Intent();
        switch(view.getId()){
            case R.id.btnLogin:
                miIntent=new Intent(Login.this,MainActivity.class);
                break;

            case R.id.loginText:
                miIntent=new Intent(Login.this,Register.class);
                break;

        }
        startActivity(miIntent);


    }
}
