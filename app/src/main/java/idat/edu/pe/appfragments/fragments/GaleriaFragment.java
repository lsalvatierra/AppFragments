package idat.edu.pe.appfragments.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import idat.edu.pe.appfragments.R;
import idat.edu.pe.appfragments.adapters.FloresAdapter;
import idat.edu.pe.appfragments.model.Flores;

public class GaleriaFragment extends Fragment {

    private RecyclerView rvdatos;
    private FloresAdapter adapter;
    ArrayList<Flores> lstflores;
    //Declaramos una cola de peticiones.
    private RequestQueue coladepeticiones;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);
        rvdatos = view.findViewById(R.id.rvdatos);
        rvdatos.setLayoutManager(new GridLayoutManager(
                getActivity(), 3
        ));
        adapter = new FloresAdapter(getContext());
        rvdatos.setAdapter(adapter);
        lstflores = new ArrayList<>();
        //Instanciamos la cola de peticiones.
        coladepeticiones = Volley.newRequestQueue(getActivity());
        ConsumirWS();
        return view;
    }

    private void ConsumirWS(){
        //Definir una variable para almacenar el URL del servicio.
        String url = "https://pixabay.com/api/?key=12544769-ce772d6f6df4078b74b23c3cf&q=yellow+flowers&image_type=photo&pretty=true";
        //Instanciar el objeto request para que sea agregado a la cola de peticiones
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject objflor = jsonArray.getJSONObject(i);
                                lstflores.add(new Flores(objflor.getString("user"),
                                        objflor.getString("webformatURL")));
                            }
                            adapter.agregarelementos(lstflores);
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        coladepeticiones.add(request);
    }
}
