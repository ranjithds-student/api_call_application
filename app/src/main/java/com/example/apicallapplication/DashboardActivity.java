package com.example.apicallapplication;

import static android.graphics.Color.RED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicallapplication.adapter.DashBoardAdapter;
import com.example.apicallapplication.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private TextView postTitleTV, apiResponseTV;
    private Button apiCallBTN;

    private RecyclerView dashBoardRV;
    private ProgressBar progressBar;
    private AppCompatButton loginBTN;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        postTitleTV = findViewById(R.id.postTitleTV);
//        apiResponseTV = findViewById(R.id.apiResponseTV);
        apiCallBTN = findViewById(R.id.apiCallBTN);
        dashBoardRV = findViewById(R.id.dashBoardRV);
        progressBar = findViewById(R.id.progressBar);
        loginBTN = findViewById(R.id.loginBTN);
        dashBoardRV.setLayoutManager(new LinearLayoutManager(this));

        //To get the data after resuming app and back from opening it
        isApiBtnClicked();


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isLogin = true;


                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                finish();
            }
        });
        apiCallBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //To get the data after resuming app and back from opening it
                SharedPreferences preferences = getSharedPreferences("API", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("IS_LOGIN_SUCCESS", "success");
                editor.putBoolean("apiButtonClicked", true);
                editor.apply();
                editor.commit();

                // Show ProgressBar
                progressBar.setVisibility(View.VISIBLE);
                getAPIData();
            }
        });

    }

    private void isApiBtnClicked() {

        SharedPreferences preferences = getSharedPreferences("API", MODE_PRIVATE);
        if (preferences.contains("apiButtonClicked")) {
            getAPIData();
        } else {
            Toast.makeText(this, "Login needed", Toast.LENGTH_SHORT).show();
        }
    }

    private void getAPIData() {

        Call<List<PostResponse>> call = APIController.getInstance().getPostAPI().getPosts();
        call.enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                // Hide ProgressBar
                progressBar.setVisibility(View.GONE);
                List<PostResponse> responseList = response.body();
                DashBoardAdapter adapter = new DashBoardAdapter(responseList);
                dashBoardRV.setAdapter(adapter);

            }

            @Override
            public void onFailure(@NonNull Call<List<PostResponse>> call, Throwable t) {
                apiResponseTV.setText(t.getMessage());
                apiResponseTV.setTextColor(RED);
                apiResponseTV.setText("");
            }
        });
    }
}