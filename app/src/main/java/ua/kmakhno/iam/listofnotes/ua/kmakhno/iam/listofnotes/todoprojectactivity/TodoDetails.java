package ua.kmakhno.iam.listofnotes.ua.kmakhno.iam.listofnotes.todoprojectactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import ua.kmakhno.iam.listofnotes.R;
import ua.kmakhno.iam.listofnotes.ua.kmakhno.iam.listofnotes.todoprojectojects.TodoDocument;


/**
 * Created by I am on 22.04.2016.
 */
public class TodoDetails extends Activity {

    private EditText editText;
    private TodoDocument todoDocument;

    public static final int RESULT_SAVE = 100;
    public static final int RESULT_DELETE = 101;
    final static String TAG_TODO = "ua.kmakhno.iam.listofnotes.tododetails";
    public static final int NAME_LENGTH = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);

        editText = (EditText)findViewById(R.id.todo_textView);

        todoDocument = (TodoDocument)getIntent().getSerializableExtra(TodoList.TODO_DOCUMENT);
        //setTitle(todoDocument.getName());
        editText.setText(todoDocument.getContent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.todo_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.back_details:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete_confirm);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_DELETE, getIntent());
                        finish();
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog  = builder.create();
                dialog.show();
                return true;
            case R.id.create_todo_details:
                editText.setText("");
                return true;
            case R.id.save_details:
                saveDocument();
                finish();
                return true;
            case android.R.id.home:
                if(editText.getText().toString().trim().length() == 0){
                    setResult(RESULT_CANCELED);
                }else{
                    saveDocument();
                }
                    finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDocument(){
        StringBuilder sb = new StringBuilder(editText.getText());
         todoDocument.setContent(sb.toString());

        if(sb.length()> NAME_LENGTH){
            sb.delete(NAME_LENGTH, sb.length()).append("...");
        }
        String tmpName = sb.toString().trim().split("\n")[0];
        String name  = (tmpName.length() > 0) ? tmpName : todoDocument.getName();

        todoDocument.setName(name);
        setResult(RESULT_SAVE, getIntent());
    }


}
