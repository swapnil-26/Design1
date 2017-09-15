package tcs.remoteinternship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyDialog1 extends DialogFragment implements DialogInterface.OnClickListener /*of android.content.dialog package */{
    //Context c;
    private Context context;

//save the context recievied via constructor in a local variable

    public MyDialog1(Context context){
        this.context=context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //builder.setIcon(R.drawable.ic);
        //builder.setTitle("Deactivate Services");
        builder.setMessage("You need an Internet Connection for better results");
        builder.setPositiveButton("Ok",this);
        AlertDialog dialog=builder.create();

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

}
