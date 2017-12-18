package com.labs.android.lab7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.Message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                "deleting product...", Toast.LENGTH_SHORT);
                        toast.show();
                        ((EditProductMenu) getActivity()).okClicked();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                "keeping product...", Toast.LENGTH_SHORT);
                        toast.show();
                        ((EditProductMenu) getActivity()).cancelClicked();
                    }
                });
        return builder.create();
    }
}
