package com.example.mybroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage

class SmsReceiver : BroadcastReceiver() {

    companion object {
        private val TAG = SmsReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {

    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        val format = bundle.getString("format")
        currentSMS = if (Build.VERSION.SDK_INT >= 23){
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else SmsMessage.createFromPdu(aObject as ByteArray)
        return currentSMS
    }
}