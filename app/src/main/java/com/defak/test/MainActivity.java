package com.defak.test;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
                step1();
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

    private void step1(){
        final Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog_recover_pass_step1);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText edtmssv =  dialog1.findViewById(R.id.edt_mssv);
        Button btncontinue = dialog1.findViewById(R.id.btn_continue);
        dialog1.show();
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step2();
            }
        });
    }
    private void step2(){
        final Dialog dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.dialog_recover_pass_step2);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText edtmssv =  dialog2.findViewById(R.id.edt_verify_code);
        Button btnreturn = dialog2.findViewById(R.id.btn_return_step2);
        Button btncontinue = dialog2.findViewById(R.id.btn_continue_step2);
        dialog2.show();
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step3();
            }
        });
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });
    }

    private void step3() {
        final Dialog dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.dialog_recover_pass_step3);
        dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText edtnewpwd =  dialog3.findViewById(R.id.edt_new_pwd);
        EditText edtconfirm =  dialog3.findViewById(R.id.edt_confirm_pwd);
        Button btnreturn = dialog3.findViewById(R.id.btn_return_step3);
        Button btnchangepwd = dialog3.findViewById(R.id.btn_change_pwd);
        dialog3.show();
        btnchangepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Địt con mẹ mày Phát!", Toast.LENGTH_SHORT).show();
            }
        });
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog3.dismiss();
            }
        });
    }
}


