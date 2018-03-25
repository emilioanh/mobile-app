package com.defak.test;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TKBAddDialog extends DialogFragment {
    EditText subject;
    EditText room;
    Button add_tkb;
    Button cancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setContentView(R.layout.dialog_new_tkb);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.88);
//        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        return dialog;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.dialog_new_tkb, null);
//
//        subject = view.findViewById(R.id.edt_subj);
//        room = view.findViewById(R.id.edt_room);
//        add_tkb = view.findViewById(R.id.btn_add_tkb);
//        cancel = view.findViewById(R.id.btn_cancel_tkb);
//
//        add_tkb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Địt con mẹ mày Phát!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        return view;
//    }

    @Override
    public void onStart() {
        super.onStart();
        mapping();
    }

    /**
     * anh xa cac phan tu tren file layout thanh
     * cac bien de xu ly
     */
    private void mapping(){
        View view = getDialog().getWindow().getDecorView();
        subject = view.findViewById(R.id.edt_subj);
        room = view.findViewById(R.id.edt_room);
        add_tkb = view.findViewById(R.id.btn_add_tkb);
        cancel = view.findViewById(R.id.btn_cancel_tkb);

        add_tkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Địt con mẹ mày Phát!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
