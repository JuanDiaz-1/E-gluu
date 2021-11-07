package mx.ita.navdrawer;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class Login extends Activity {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button BtnLogin;



    private String stremail ="";
    private String strpassword ="";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_NavDrawer);
        setContentView(R.layout.activity_login);


        TextEmail = (EditText) findViewById(R.id.correoelectronico);
        TextPassword = (EditText) findViewById(R.id.password);
        BtnLogin = (Button) findViewById(R.id.btnLogin);


        mAuth = FirebaseAuth.getInstance();


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stremail= TextEmail.getText().toString();
                strpassword = TextPassword.getText().toString();

                if (!stremail.isEmpty() && !strpassword.isEmpty()){

                    loginUser();
                }
                else{
                    Toast.makeText(Login.this,"Complete los campos",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void loginUser(){

        mAuth.signInWithEmailAndPassword(stremail,strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(Login.this, "No se pudo loguear", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        if(mAuth.getCurrentUser()!= null){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
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
