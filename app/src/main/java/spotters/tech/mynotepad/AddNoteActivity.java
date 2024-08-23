package spotters.tech.mynotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleinput = findViewById(R.id.titleinput);
        EditText descriptioninput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleinput.getText().toString();
                String description = descriptioninput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createOnject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note Saved",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    private static class Realm {
        public static Realm getDefaultInstance() {
            return null;
        }

        public static void init(Context applicationContext) {
        }

        public Note createOnject(Class<Note> noteClass) {
            return null;
        }

        public void beginTransaction() {
        }

        public void commitTransaction() {
        }
    }
}