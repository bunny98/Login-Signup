package com.example.shiva.try1;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import twitter4j.Paging;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.auth.AccessToken;

public class DashboardActivity extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button LogOUT ;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    //@SuppressLint("SetTextI18n")
    public static final String TAG="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

       Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(login.userEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("z3RgnDTJbZ2PcH0u0YRj1zIIp", "cEL5Pw360tpOLntZKRceXdJLwWqpbTlKWUml8DDo3A1lq4vqiP");
        AccessToken accessToken = new AccessToken("1153381672148094976-dpR7zoup0ZmTo8XJ9GWv5kYtcTcoki", "P8taY8eGm4oDQhpaX63gPb0tVkRrG0pao4UUSimR9sEm3");
        twitter.setOAuthAccessToken(accessToken);

        int totalTweets = 20;// no of tweets to be fetched
        Paging paging = new Paging(1, totalTweets);
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                Log.v("Timeline", status.getUser().getName() + ":" +
                        status.getText());
            }

        }catch (Exception e){ Log.v("Timeline", "EXCEPTION");}

        // Adding click listener to Log Out button.

        LogOUT.setOnClickListener(new View.OnClickListener() {
           // @Override
            public void onClick(View v) {


                //Finishing current DashBoard activity on button click
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent=new Intent(DashboardActivity.this,login.class);
                startActivity(intent);
//               if (v.getId() == R.id.button1) {
//                    mAuth.getInstance()
//                            .signOut(this)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    // user is now signed out
//                                    startActivity(new Intent(DashboardActivity.this, login.class));
//                                    finish();
//                                }
//                            });
//                }

            }
        });

    }



}