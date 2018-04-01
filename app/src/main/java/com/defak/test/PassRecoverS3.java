package com.defak.test;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS3 extends DialogFragment{
    EditText new_pass;
    EditText pass_confirm;
    Button cont;
    Button back;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_recover_pass_step3);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapping();
    }

    private void mapping(){
        View view = getDialog().getWindow().getDecorView();
        new_pass = view.findViewById(R.id.edt_new_pwd);
        pass_confirm = view.findViewById(R.id.edt_confirm_pwd);
        cont = view.findViewById(R.id.btn_change_pwd);
        back = view.findViewById(R.id.btn_return_step3);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Địt con mẹ mày Phát!", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }
}
