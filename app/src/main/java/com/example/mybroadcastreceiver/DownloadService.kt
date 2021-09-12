package com.example.mybroadcastreceiver

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class DownloadService: JobIntentService() {
    companion object{
        val TAG: String = DownloadService::class.java.simpleName
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null){
            enqueueWork(this, this::class.java, 101, intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    // Di sini kita akan menjalankan Intent Service yang akan melakukan proses mengunduh file secara Asynchronous di background
    override fun onHandleWork(intent: Intent) {
        /*
        DownloadService ini hanya melakukan proses sleep() selama 5 detik dan kemudian mem-broadcast sebuah IntentFilter dengan Action yang telah ditentukan, ACTION_DOWNLOAD_STATUS
         */
        Log.d(TAG, "Download Service Dijalankan")
        try{
            Thread.sleep(5000)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }

        val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
        sendBroadcast(notifyFinishIntent)

        /*
        Ketika proses pengunduhan berkas tersebut selesai, service akan mem-broadcast sebuah event dan akan ada Activity yang merespon
         */
    }


}