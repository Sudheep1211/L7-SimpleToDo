package sg.edu.rp.c346.id20024402.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTodo;
    Button btnAdd;
    Button btnClear;
    ListView lvTodolist;
    Spinner spnAction;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTodo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClear = findViewById(R.id.buttonClearItem);
        lvTodolist = findViewById(R.id.listViewToDo);
        spnAction = findViewById(R.id.spinnerAction);
        btnDelete = findViewById(R.id.buttonDeleteItem);

        ArrayList<String> alTodolist = new ArrayList<>();
        ArrayAdapter<String> aaTodolist = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodolist);

        lvTodolist.setAdapter(aaTodolist);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertask = etTodo.getText().toString();
                alTodolist.add(usertask);
                aaTodolist.notifyDataSetChanged();
                etTodo.setText(null);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodolist.clear();
                aaTodolist.notifyDataSetChanged();
                etTodo.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!alTodolist.isEmpty()){
                    int pos = Integer.parseInt(etTodo.getText().toString());
                    if(pos < alTodolist.size()){
                        alTodolist.remove(pos);
                        aaTodolist.notifyDataSetChanged();
                        etTodo.setText(null);
                    } else{
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }

                } else if(alTodolist.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();

                }
            }
        });

        spnAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTodo.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnDelete.setEnabled(true);
                        etTodo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}