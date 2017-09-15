package tcs.remoteinternship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by abc on 09-07-2016.
 */
public class MyDialog extends DialogFragment implements DialogInterface.OnClickListener /*of android.content.dialog package */{
    //Context c;
    private Context context;

//save the context recievied via constructor in a local variable

    public MyDialog(Context context){
        this.context=context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //builder.setIcon(R.drawable.ic);
        //builder.setTitle("Deactivate Services");
        builder.setMessage("Deactivate Services");
        builder.setPositiveButton("Yes",this);
        builder.setNegativeButton("No",this);
        AlertDialog dialog=builder.create();

        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        if(which==-1)
        {
            PackageManager pm  = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, SMSBReceiver.class);
            pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);

           /* PackageManager pm1  = context.getPackageManager();
            ComponentName componentName1 = new ComponentName(context, CallBReceiver.class);
            pm1.setComponentEnabledSetting(componentName1,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);*/
            Toast.makeText(context, "Service Deactivated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            PackageManager pm  = context.getPackageManager();
            ComponentName componentName = new ComponentName(context, SMSBReceiver.class);
            pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

           /* PackageManager pm3  = context.getPackageManager();
            ComponentName componentName2 = new ComponentName(context, CallBReceiver.class);
            pm3.setComponentEnabledSetting(componentName2,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);*/
            Toast.makeText(context, "Service Activated", Toast.LENGTH_SHORT).show();
        }

    }
}
