package com.example.cecildot.contentprov;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText name,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btncon);
        contact = (EditText) findViewById(R.id.etcontact);
        name =(EditText)findViewById(R.id.etname);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callcontact =  new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(callcontact,123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123&&resultCode==RESULT_OK){
            Uri selected = data.getData();
            ContentResolver resolver = getContentResolver();
            String[] columns = new String[]{
              ContactsContract.CommonDataKinds.Phone._ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };

            Cursor cursor = resolver.query(selected,columns,null,null,null);
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(columns[0]));
            String username = cursor.getString(cursor.getColumnIndex(columns[1]));
            String number = cursor.getString(cursor.getColumnIndex(columns[2]));
            name.setText(""+username);
            contact.setText(""+number);
        }
    }
}
