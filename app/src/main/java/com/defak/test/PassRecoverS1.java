package com.defak.test;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by DEFAK on 4/1/2018.
 */

public class PassRecoverS1 extends DialogFragment{
    EditText masv;
    Button cont;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_recover_pass_step1);
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
        masv = view.findViewById(R.id.edt_mssv);
        cont = view.findViewById(R.id.btn_continue);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassRecoverS2 step2 = new PassRecoverS2();
                step2.show(getFragmentManager(),"rec_pass_2");
            }
        });
    }
}
