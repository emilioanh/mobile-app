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

public class PassRecoverS1 extends DialogFragment{
    EditText mailsv;
    Button cont;
    String shit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Bundle mArgs = getArguments();
        shit = mArgs.getString("fuck");
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
        mailsv = view.findViewById(R.id.edt_mailsv);
        cont = view.findViewById(R.id.btn_continue);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String email = mailsv.getText().toString().trim();
//                String subject = "PTIT App - Verification code for new password";
//                String message = "Hi "+ email +",\nYou have recently requested for new password since you forgot yours.\nHere is your verification code: concac\nPlease input into our app to verify your request.";
//                SendMail sm = new SendMail(getActivity(), email, subject, message);
//                sm.execute();
                mailsv.setText(shit);
//                PassRecoverS2 step2 = new PassRecoverS2();
//                step2.show(getFragmentManager(),"rec_pass_2");
            }
        });
    }
}
