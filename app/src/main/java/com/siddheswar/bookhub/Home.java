package com.siddheswar.bookhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class Home extends AppCompatActivity {
    EditText EtBookName;

    String[] title, image, Preview, Info;
    ImageView imgSearch,imgBackHome;
    RecyclerView recyclerView;
    ActionBarDrawerToggle actionbartoggle;

    String searchUrl;
    Uri uri;
    Uri.Builder buider;
    String querry;
    Toolbar Toolbar;
    NavigationView NavigationView;
    DrawerLayout drawerLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView = findViewById(R.id.NavigationView);


        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.menuLogout:
                        Toast.makeText(Home.this, "LogOut", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuPerson:
                        Toast.makeText(Home.this, "person detail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuShare:
                        Toast.makeText(Home.this,"SHARE",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(Home.this, "Something Unusual Occured", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        EtBookName = findViewById(R.id.editTextTextPersonName);
        setUpToolBar();
        title = new String[10];
        image = new String[10];

        Preview = new String[10];
        Info = new String[10];
        imgSearch = findViewById(R.id.imgSearch);
        imgBackHome = findViewById(R.id.imgBackHome);
        recyclerView = findViewById(R.id.recycleViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecyclerHomeAdapter(title, image, Preview, Info, getApplicationContext()));



        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = EtBookName.getText().toString();
                if(bookName.equals(null))
                {
                    Toast.makeText(Home.this,"Please enter a querry",Toast.LENGTH_LONG).show();
                }

                searchUrl = "https://www.googleapis.com/books/v1/volumes?q=";
                querry = bookName.replace( " ","+");
                uri= Uri.parse(searchUrl+querry);
                buider = uri.buildUpon();
                String X= buider.toString();


                JSONParse(X);
                imgBackHome.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });






    }

    private void setUpToolBar() {
        drawerLayout = findViewById(R.id.drawer);
        Toolbar = findViewById(R.id.ToolBar);

        actionbartoggle = new ActionBarDrawerToggle(this,drawerLayout,Toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionbartoggle);
        actionbartoggle.syncState();


    }


    private void JSONParse(String X) {

        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, X, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        JSONObject volumeInfo = jo.getJSONObject("volumeInfo");
                        title[i] = volumeInfo.getString("title");
                        Preview[i] = volumeInfo.getString("previewLink");
                        Info[i] = volumeInfo.getString("infoLink");

                        image[i]=volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Home.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }


        });

        requestQueue.add(jsonObjectRequest);






    }

}