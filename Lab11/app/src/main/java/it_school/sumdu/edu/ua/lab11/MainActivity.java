package it_school.sumdu.edu.ua.lab11;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private Button btnSave;
    private DocViewModel documentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        btnSave = findViewById(R.id.buttonSave);

        documentViewModel = new ViewModelProvider(this).get(DocViewModel.class);
        documentViewModel.getDocumentLiveData().observe(this, new Observer<Document>() {
            @Override
            public void onChanged(Document document) {
                if(document != null && document.getContent() != null) {
                    textView.setText(document.getContent());
                    editText.setText(document.getContent());
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString().trim();
                documentViewModel.updateDocument(new Document(1, content));
            }
        });
    }
}