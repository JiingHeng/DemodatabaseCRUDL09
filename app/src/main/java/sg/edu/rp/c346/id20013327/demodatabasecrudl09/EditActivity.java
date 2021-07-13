package sg.edu.rp.c346.id20013327.demodatabasecrudl09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    TextView tvID;
    EditText etContent;
    Button btnUpdate, btnDelete;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        tvID = findViewById(R.id.tvID);
        etContent = findViewById(R.id.etContent);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        tvID.setText("ID: " + data.getId());
        etContent.setText(data.getNoteContent());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
//                if(data.getId() != 0) {
                    data.setNoteContent(etContent.getText().toString());
                    dbh.updateNote(data);
                    Toast.makeText(EditActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    dbh.close();
//                } else {
//                    Toast.makeText(EditActivity.this, "No more data to be updated", Toast.LENGTH_SHORT).show();
//                }
                Intent backToMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
//                if(data.getId() != 0) {
                    dbh.deleteNote(data.getId());
                    Toast.makeText(EditActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(EditActivity.this, "There is no more data to be deleted", Toast.LENGTH_SHORT).show();
//                }
                Intent backToMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(backToMain);
            }
        });


    }
}