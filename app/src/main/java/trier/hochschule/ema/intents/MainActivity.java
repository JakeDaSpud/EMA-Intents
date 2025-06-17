package trier.hochschule.ema.intents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import trier.hochschule.ema.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ---

        Log.d("buttonName", "Webpage: " + binding.buttonOpenWebpage.getId());
        Log.d("buttonName", "Location: " + binding.buttonOpenLocation.getId());
        Log.d("buttonName", "ShareText: " + binding.buttonShareText.getId());
        Button buttonOpenWebpage = findViewById(R.id.button_open_webpage);
        Button buttonOpenLocation = binding.buttonOpenLocation;
        Button buttonOpenShareText = binding.buttonShareText;
        Button buttonLaunchSecondActivity = binding.buttonSecondActivity;
        Button buttonLaunchGoogle = binding.buttonGoogleActivity;

        buttonOpenWebpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage("https://www.github.com");
            }
        });

        buttonOpenLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
            }
        });

        buttonOpenShareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText("Hello from CodeLab!");
            }
        });

        buttonLaunchSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity(v);
            }
        });

        buttonLaunchGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGoogle(v);
            }
        });

    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(e.toString(), e.getMessage());
        };
    }

    private void openLocation(String geoLocation) {
        Uri location = Uri.parse(geoLocation);
        Intent intent = new Intent(Intent.ACTION_VIEW, location);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(e.toString(), e.getMessage());
        };
    }

    private void shareText(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(e.toString(), e.getMessage());
        };
    }

    private void startSecondActivity(View v) {
        Log.d("startSecondActivity()", "Starting second activity...");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void startGoogle(View v) {
        Uri url = Uri.parse("https://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        intent.setPackage("com.android.chrome");

        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(e.toString(), "Error");
        }
    }
}