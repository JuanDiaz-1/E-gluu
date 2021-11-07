package mx.ita.navdrawer;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends Activity {

    private EditText TextEmail;
    private EditText TextPassword;

    private EditText TextConfirmPassword;
    private Button BtnSignUp;
    private CheckBox CheckBoxTerminos;
    private TextView loginText;


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;



    private String stremail="";
    private String strpassword="";
    private String strconfirmpassword="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        TextEmail = (EditText) findViewById(R.id.email);
        TextPassword = (EditText) findViewById(R.id.password);
        TextConfirmPassword= (EditText) findViewById(R.id.confirmarcontraseña);
        BtnSignUp = (Button) findViewById(R.id.btnRegistro);
        CheckBoxTerminos = (CheckBox) findViewById(R.id.checkBoxterminos);
        loginText = (TextView) findViewById(R.id.loginText);


        BtnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                stremail = TextEmail.getText().toString();
                strpassword = TextPassword.getText().toString();
                strconfirmpassword = TextConfirmPassword.getText().toString();



                if (!stremail.isEmpty() && !strpassword.isEmpty() && !strconfirmpassword.isEmpty() && CheckBoxTerminos.isChecked()){
                    if(strpassword.equals(strconfirmpassword)){

                            if (strpassword.length()>=6 && CheckBoxTerminos.isChecked()){
                             registerUser();
                            Toast.makeText(Register.this,"Te registraste satisfactoriamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));
                            finish();
                            }else{
                                     Toast.makeText(Register.this,"El password debe de tener almenos 6 caracteres", Toast.LENGTH_SHORT).show();
                                   }

                    }else {
                        Toast.makeText(Register.this,"la contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Register.this,"Debe de completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }




    private void  registerUser(){

        mAuth.createUserWithEmailAndPassword(stremail, strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("email", stremail);
                    map.put("password", strpassword);
                    map.put("confimrpassword", strconfirmpassword);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("User").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Toast.makeText(Register.this, "Te registraste satisfactoriamente", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));
                                finish();
                            }

                            else{
                                Toast.makeText(Register.this, "No se pudieron registrar los datos correctamente", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                }else {
                    Toast.makeText(Register.this,"No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }











    public void onClick(View view){

        switch(view.getId()){

            case R.id.loginText:
                startActivity(new Intent(Register.this,Login.class));
                break;
        }

    }
}