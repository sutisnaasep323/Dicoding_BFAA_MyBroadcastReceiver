package com.example.mybroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.lang.Exception

class SmsReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = SmsReceiver::class.java.simpleName
    }

    // metode onReceive()receiver akan memproses metadata dari SMS yang masuk
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try{
            if (bundle != null){
                /*
                  Bundle dengan key "pdus" sudah merupakan standar yang digunakan oleh system
               */
                val pdusObj = bundle.get("pdus") as Array<*>

                for (aPdusObj in pdusObj){
                    val currentMessage = getIncomingMessage(aPdusObj as Any, bundle)
                    val senderNum = currentMessage.displayOriginatingAddress
                    val message = currentMessage.displayMessageBody

                    Log.d(TAG,"senderNum: $senderNum; message: $message")

                    // ReceiverActivity akan dijalankan dengan membawa data melalui sebuah intent showSmsIntent
                    val showSmsIntent = Intent(context, SmsReceiverActivity::class.java)
                    showSmsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // akan menjalankan Activity pada task yang berbeda. Bila Activity tersebut sudah ada di dalam stack, maka ia akan ditampilkan ke layar.
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum)
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message)
                    context.startActivity(showSmsIntent)

                }
            }
        } catch (e : Exception){
            Log.d(TAG, "Exception smsReceiver : $e")
        }
    }

    /*
    kelas SmsManager dan SmsMesssage untuk melakukan pemrosesan SMS. Untuk memperoleh obyek dari
    kelas SmsMessage, yaitu obyek currentMessage, kita menggunakan metode getIncomingMessage().
    Metode ini akan mengembalikan currentMessage berdasarkan OS yang dijalankan oleh perangkat Android.
     */
    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        val format = bundle.getString("format")
        currentSMS = if (Build.VERSION.SDK_INT >= 23){
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else SmsMessage.createFromPdu(aObject as ByteArray)
        return currentSMS
    }
}