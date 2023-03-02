package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DownloadRunners extends AppCompatActivity {

    private EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_runners);

        url = (EditText) findViewById(R.id.url);
        Button download = (Button) findViewById(R.id.download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUrl = url.getText().toString();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(getUrl));
                String title = URLUtil.guessFileName(getUrl,null,null);
                request.setTitle(title);
                request.setDescription("Downloading...");
                String cookie = CookieManager.getInstance().getCookie(getUrl);
                request.addRequestHeader("cookie",cookie);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
//                request.setDestinationInExternalFilesDir(Environment.DIRECTORY_DOCUMENTS,)
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, "IMRA/runners.xml");
                DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(DownloadRunners.this,"Downloading", Toast.LENGTH_SHORT).show();
            }
        });
    }
}