package com.defak.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public class client{
        String name;
        String addr;
        String state;
    }
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    EditText name;
    EditText address;
    Button add;
    Button exit;
    Button plus;
    Button plus2;
    RadioGroup state;
    RadioButton selectedRadioButton;
    ListView lv_shit;
    ArrayList<Client> clientArrayList;
    ClientAdapter clientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        clientArrayList = new ArrayList<>();
        // Example of a call to a native method
        clientAdapter = new ClientAdapter(this, R.layout.client, clientArrayList);
        buttonEvent();
        lv_shit.setAdapter(clientAdapter);

    }

    private void buttonEvent(){
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePass();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client newClient = new Client();
                newClient.setName(name.getText().toString());
                newClient.setAddr(address.getText().toString());
                if (state.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Hãy chọn Trạng thái!", Toast.LENGTH_SHORT).show();
                } else {
                    // get selected radio button from radioGroup
                    int selectedId = state.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = findViewById(selectedId);
                    newClient.setState(selectedRadioButton.getText().toString());
                    clientArrayList.add(newClient);
                    clientAdapter.notifyDataSetChanged();
                }
            }
        });
        lv_shit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clientArrayList.remove(i);
                clientAdapter.notifyDataSetChanged();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setControl(){
        name = (EditText) findViewById(R.id.edtxt_name);
        address = (EditText) findViewById(R.id.edtxt_addr);
        add = (Button)findViewById(R.id.btn_add);
        exit = (Button)findViewById(R.id.btn_exit);
        state = (RadioGroup)findViewById(R.id.rdg_stt);
        plus = (Button)findViewById(R.id.btn_test);
        plus2 = (Button)findViewById(R.id.btn_plus2);
        lv_shit = (ListView)findViewById(R.id.listview_item);
    }

    private void showDialog(){
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_new_tkb);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        EditText edtsubj =  dialog.findViewById(R.id.edt_subj);
//        EditText edtroom =  dialog.findViewById(R.id.edt_room);
//        Button btnadd = dialog.findViewById(R.id.btn_add_tkb);
//        Button btncancel = dialog.findViewById(R.id.btn_cancel_tkb);
//        btnadd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Địt con mẹ mày Phát!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        dialog.show();
        TKBAddDialog add_tkb_dialog = new TKBAddDialog();
        add_tkb_dialog.show(getFragmentManager(),"add_tkb");
    }

    private void changePass(){
        PassRecoverS1 step1 = new PassRecoverS1();
        Bundle args = new Bundle();
        args.putString("fuck", "cai lol que");
        step1.setArguments(args);
        step1.show(getFragmentManager(),"rec_pass_1");
    }
}


