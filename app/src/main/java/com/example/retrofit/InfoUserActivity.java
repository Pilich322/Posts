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
    public InfoUserActivity(Post post){
        this.post = post;
    }
    int userID = 0;
    Post post;
    TextView txtOutput;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        txtOutput = findViewById(R.id.TextViewUser);
        Intent intent = getIntent();
        userID = intent.getIntExtra("userId",0);
        Log.d("LOH", userID + "");
        getUser();

    }

    public void getUser(){
        Call<List<Users>> listUsers = apiInterface.getUser(userID);
        listUsers.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()){
                    Log.e("CODE", response.code() + "");
                    return;
                }
                List<Users> users = response.body();
                for (Users user : users)
                txtOutput.append("id: " + user.getId() + "\n");
//                txtOutput.append("name: " + user. + "\n");
//                txtOutput.append("username: " + user.getUsername() + "\n");
//                txtOutput.append("email: " + user.getEmail() + "\n");
//                txtOutput.append("address: " + user.getAddress() + "\n");
//                txtOutput.append("\t" + "street: " + user.getAddress().getStreet() + "\n");
//                txtOutput.append("\t" + "suite: " + user.getAddress().getSuite() + "\n");
//                txtOutput.append("\t" + "city: " + user.getAddress().getCity() + "\n");
//                txtOutput.append("\t" + "city: " + user.getAddress().getZipcode() + "\n");
//                txtOutput.append("\t" + "geo: " + "\n");
//                txtOutput.append("\t\tlat: " + user.getAddress().getGeo().getLat() + "\n");
//                txtOutput.append("\t\tlng: " + user.getAddress().getGeo().getLng() + "\n");
//                txtOutput.append("phone" + user.getPhone() + "\n");
//                txtOutput.append("website" + user.getWebsite() + "\n");
//                txtOutput.append("\t"+ "company" + "\n");
//                txtOutput.append("\t\tname: " + user.getCompany().getName() + "\n");
//                txtOutput.append("\t\tcatchPhrase: " + user.getCompany().getCatchPhase() + "\n");
//                txtOutput.append("\t\tbs: " + user.getCompany().getBs() + "\n");
//
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.w("Code", t.getLocalizedMessage()+ "");
            }
        });
    }

}