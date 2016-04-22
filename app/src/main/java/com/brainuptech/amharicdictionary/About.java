package com.brainuptech.amharicdictionary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rog on 2/21/16.
 */

public class About extends Activity {
    private Button Finish;
    private TextView Email_Link,FaceBook_Link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Finish = (Button) findViewById(R.id.btn_ok);
        FaceBook_Link = (TextView) findViewById(R.id.txt_facebook);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
/*        Email_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to = "info@brainuptech.com";
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });*/
        FaceBook_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Brain-Up-Software-Technologies-110275605972090/"));
                startActivity(browserIntent);
            }
        });
    }
}
