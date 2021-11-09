package mx.ita.navdrawer;
import com.google.firebase.database.FirebaseDatabase;
import android.app.Application;

public class MyFirebaseApp extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}