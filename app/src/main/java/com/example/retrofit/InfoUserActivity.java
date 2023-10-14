package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.retrofit.api.ApiClient;
import com.example.retrofit.api.ApiInterface;
import com.example.retrofit.data.Post;
import com.example.retrofit.data.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoUserActivity extends AppCompatActivity {
    int userID = 0;
    TextView txtOutput;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        txtOutput = findViewById(R.id.TextViewUser);
        Intent intent = getIntent();
        userID = intent.getIntExtra("userId",0);
        getUser();
    }

    public void getUser(){
        Call<Users> listUsers = apiInterface.getUser(userID);
        listUsers.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users user = (Users) response.body();
                    txtOutput.append("ID user: " + user.getId() + "\n");
                    txtOutput.append("Name: " + user.getName()+ "\n");
                    txtOutput.append("UserName: " + user.getUsername()+ "\n");
                    txtOutput.append("Address: " + "\n");
                    txtOutput.append("\tStreet: " + user.getAddress().getStreet() + "\n");
                    txtOutput.append("\tSuite: " + user.getAddress().getSuite() + "\n");
                    txtOutput.append("\tCity: " + user.getAddress().getCity() + "\n");
                    txtOutput.append("\tZipCode: " + user.getAddress().getZipcode() + "\n");
                    txtOutput.append("\tGeo: " + "\n");
                    txtOutput.append("\t\tLat: " + user.getAddress().getGeo().getLat() + "\n");
                    txtOutput.append("\t\tLng: " + user.getAddress().getGeo().getLng() + "\n");
                    txtOutput.append("Phone: " + user.getPhone() + "\n");
                    txtOutput.append("Website: " + user.getWebsite() + "\n");
                    txtOutput.append("\tCompany:" + "\n");
                    txtOutput.append("\t\tName company: " + user.getCompany().getName()+ "\n");
                    txtOutput.append("\t\tCatchPhrase company: " + user.getCompany().getCatchPhrase() + "\n");
                    txtOutput.append("\t\tBS company: " + user.getCompany().getBs());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e("CODE", t.getMessage() + "");
            }
        });

    }

}