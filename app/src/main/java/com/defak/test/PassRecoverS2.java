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

public class PassRecoverS2 extends DialogFragment{
    EditText vrf_code;
    Button back;
    Button cont;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_recover_pass_step2);
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
        vrf_code = view.findViewById(R.id.edt_verify_code);
        cont = view.findViewById(R.id.btn_continue_step2);
        back = view.findViewById(R.id.btn_return_step2);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassRecoverS3 step3 = new PassRecoverS3();
                step3.show(getFragmentManager(),"rec_pass_3");
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
