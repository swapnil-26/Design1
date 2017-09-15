package tcs.remoteinternship;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Dell-1 on 7/25/2017.
 */


public class SMSBReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        // Get Bundle object contained in the SMS intent passed in
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        String sms_str = "";
        String sms_str1 = "";
        if (bundle != null) {
            // Get the SMS message
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];

            for (int i = 0; i < smsm.length; i++) {
                smsm[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                sms_str1=smsm[i].getOriginatingAddress();
                //sms_str += "Sent From: " + smsm[i].getOriginatingAddress();
                sms_str += "\r\nMessage: ";
                sms_str += smsm[i].getMessageBody().toString();
                sms_str += "\r\n";
            }

            // Start Application's  MainActivty activity
            Intent smsIntent = new Intent(context, Speaker.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra("sms_str", sms_str);
            smsIntent.putExtra("sms_str1", sms_str1);
            //smsIntent.putExtra("a", pitch);
            //smsIntent.putExtra("b", speechRate);
            context.startService(smsIntent);
        }



    }
}


