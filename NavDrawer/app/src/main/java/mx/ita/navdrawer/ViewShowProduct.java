package mx.ita.navdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ViewShowProduct extends Activity {
    TextView np,dp,sp,pcp,pvp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewshowproducto);
        np=findViewById(R.id.nombrep);
        dp=findViewById(R.id.detallespr);
        sp=findViewById(R.id.stockpr);
        pcp=findViewById(R.id.preciocp);
        pvp=findViewById(R.id.preciovp);
        String n1=getIntent().getStringExtra("NomP");
        String n2=getIntent().getStringExtra("DetP");
        String n3=getIntent().getStringExtra("StoP");
        String n4=getIntent().getStringExtra("PrecP");
        String n5=getIntent().getStringExtra("PrevP");
        np.setText(n1);
        dp.setText(n2);
        sp.setText(n3);
        pcp.setText(n4);
        pvp.setText(n5);

    }
}
