package com.example.alhanoufalghayhab_codingdojo_notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.alhanoufalghayhab_codingdojo_notificationsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var Bindings:ActivityMainBinding
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Bindings.root)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        showNotification()

    }



    fun showNotification()
    {
        Bindings.show.setOnClickListener {
            var notificationContent = Bindings.notcontent.text

            bodyOfNotification(notificationContent.toString())



        }

    }


    fun bodyOfNotification(content:String)
    {
        val message = "Your $content is ready"
        val intent = Intent(this@MainActivity, MealActivity::class.java)
        intent.putExtra("meal",message)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)





        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(
                        channelId,
                        description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this@MainActivity, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this@MainActivity.resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Alhanouf notification")
                        .setContentText(message)
                } else {

                    builder = Notification.Builder(this@MainActivity)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(
                            BitmapFactory.decodeResource(
                                this@MainActivity.resources,
                                R.drawable.ic_launcher_background
                            )
                        )
                }


            }
            override fun onFinish() {
                notificationManager.notify(88, builder.build())
            }
        }.start()


    }
}