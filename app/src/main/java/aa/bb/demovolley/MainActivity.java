package aa.bb.demovolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<Touriste> AA = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,Touriste.getLstTouristes());
        ListView LV = findViewById(R.id.LV1);
        LV.setAdapter(AA);

    }
}