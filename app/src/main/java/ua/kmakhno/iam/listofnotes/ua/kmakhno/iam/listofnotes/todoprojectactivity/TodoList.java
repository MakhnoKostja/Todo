package ua.kmakhno.iam.listofnotes.ua.kmakhno.iam.listofnotes.todoprojectactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import ua.kmakhno.iam.listofnotes.R;
import ua.kmakhno.iam.listofnotes.ua.kmakhno.iam.listofnotes.todoprojectojects.TodoDocument;


/**
 * Created by I am on 22.04.2016.
 */
public class TodoList extends Activity implements AdapterView.OnItemClickListener {

    public static String TODO_DOCUMENT = "ua.kmakhno.iam.listofnotes.TodoDocument";
    public static int TODO_DETAILS_REQUEST = 1;


    private ListView listView;
    private List<TodoDocument> listDocument;
    private ArrayAdapter<TodoDocument> arrayAdapter;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        emptyTextView = (TextView)findViewById(R.id.emptyElement);

        fillTodo();
    }

    private void fillTodo() {

        listDocument = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_todo_row, listDocument);

        if(listView.getCount() == 0){
            listView.setEmptyView(emptyTextView);
        }
        listView.setAdapter(arrayAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.todo_list_menu:
                Log.e(TODO_DOCUMENT, "Заметки");
                return true;
            case R.id.task:
                TodoDocument todoDocument = new TodoDocument();
                todoDocument.setName(getResources().getString(R.string.create_todo));
                showDocument(todoDocument);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDocument(TodoDocument todoDocument) {

        Intent inetntList = new Intent(this, TodoDetails.class);
        inetntList.putExtra(TODO_DOCUMENT, todoDocument);
        startActivityForResult(inetntList, TODO_DETAILS_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TODO_DETAILS_REQUEST) {
            switch (resultCode) {
                case TodoDetails.RESULT_DELETE:
                    TodoDocument todoDocument = (TodoDocument) data.getSerializableExtra(TODO_DOCUMENT);
                    deleteDocument(todoDocument);
                    break;

                case TodoDetails.RESULT_SAVE:
                    todoDocument = (TodoDocument) data.getSerializableExtra(TODO_DOCUMENT);
                    addDocument(todoDocument);
                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TodoDocument todoDocument = (TodoDocument) parent.getAdapter().getItem(position);
        showDocument(todoDocument);
    }

    private void addDocument(TodoDocument todoDocument){
        listDocument.add(todoDocument);
        todoDocument.setNumber(listDocument.indexOf(todoDocument));
        arrayAdapter.notifyDataSetChanged();
    }

    private void deleteDocument(TodoDocument todoDocument){
        listDocument.remove(todoDocument);
        arrayAdapter.notifyDataSetChanged();
    }
}