package aa.bb.demovolley;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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

public class Touriste {

    private int id;
    private String nom;
    private String localisation;
    private static ArrayList<Touriste> lstTouristes = new ArrayList<>();

    @Override
    public String toString() {
        return "Touriste{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", localisation='" + localisation + '\'' +
                '}';
    }

    public Touriste(int id, String nom, String localisation) {
        this.id = id;
        this.nom = nom;
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
public static ArrayList<Touriste> getLstTouristes()
{
    return lstTouristes;
}

//Accès à l'API

public void chargerLstTouristes(Context context)
{
    JsonObjectRequest joReq = new JsonObjectRequest(
            Request.Method.GET,
            "http://restapi.adequateshop.com/api/Tourist",
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray ja= response.getJSONArray("data");
                    for (int i=0; i<ja.length();i++)
                    {
                        JSONObject jo = ja.getJSONObject(i);
                        //Création d'un touriste à partir des données de l'api
                        Touriste T = new Touriste(
                                jo.getInt("id"),
                                jo.getString("tourist_name"),
                                jo.getString("tourist_location"));
                        lstTouristes.add(T);

                    }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   // Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("ee",error.getMessage());
                }
            }

    );

    RequestQueue requestQueue= Volley.newRequestQueue(context);
    requestQueue.add(joReq);
// Il faut autoriser l'accès à internet dans le manift
    // il faut modifier l'attribut       android:usesCleartextTraffic="true"
    //car par défaut android n'accepte pas les requetes
    //http il faut https
    // pour Application

}

}
