package mx.ita.navdrawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import mx.ita.navdrawer.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private Button mBtnAgregar;
    private ListView mListView;
    private EditText mEditText;
    private List<String> mLista = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);*/
          mListView = root.findViewById(R.id.listview);
          mBtnAgregar = root.findViewById(R.id.btnlist);
          mEditText = root.findViewById(R.id.edlista);
      /*  homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mEditText.setText(s);
            }
        });*/

        return root;



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnlist: String texto = mEditText.getText().toString().trim();
                mLista.add(texto);
                mEditText.getText().clear();
                ArrayAdapter mAdapter  = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mLista);
                mListView.setAdapter(mAdapter);
        }
    }
}