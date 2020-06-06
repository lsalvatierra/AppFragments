package idat.edu.pe.appfragments.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import idat.edu.pe.appfragments.R;


public class CompartirFragment extends Fragment {

    private TextInputEditText etnumero;
    private Button btncalcular;

    public CompartirFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compartir, container, false);
        etnumero = view.findViewById(R.id.etnumero);
        btncalcular = view.findViewById(R.id.btncalcular);
        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numero =  Double.parseDouble(etnumero.getText().toString());
                Double modnumero = numero % 2;
                String resultado = "Es IMPAR";
                if(modnumero == 0){
                    resultado = "Es PAR";
                }
                Toast.makeText(getContext(), "El número ingresado es: "+resultado, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
