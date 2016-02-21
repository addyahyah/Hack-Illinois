package com.example.hackillinois0219;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostAdd extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);

        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.title_text);
                EditText desc = (EditText) findViewById(R.id.desc_text);
                EditText price = (EditText) findViewById(R.id.price_text);
                Log.d("BUCKS", "Adding " + title.getText() + ":" + desc.getText() + " for " + price.getText());
                finish();
            }
        });


    }




}
