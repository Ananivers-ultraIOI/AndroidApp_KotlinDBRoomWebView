package be.heh.kotlindbsql

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import be.heh.kotlindbsql.databinding.ActivityMainBinding
import be.heh.kotlindbsql.db.MyDB
import be.heh.kotlindbsql.db.User
import be.heh.kotlindbsql.db.UserRecord

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun xmlClickEvent(v: View) {
        when(v.id){
            binding.btEcrireMain.id -> ecrire()
            binding.btLireMain.id -> lire()
            binding.btLireLoginMain.id -> lireCeLogin(binding.etChildrenLogin.text.toString())
            binding.btWbHehMain.id -> afficherSiteWeb()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun lireCeLogin(login: String) {
        AsyncTask.execute{
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java,"MyDataBase"
            ).build()
            val dao= db.userDao()
            val dbL = dao?.get(login)
            var uL= User(dbL?.id?:0,dbL?.login?:"INDEFINI",dbL?.pwd?:"INDEFINI",dbL?.email?:"INDEFINI")
            binding.tvLoginMain.setTextColor(Color.RED)
            binding.tvMainPwd.setTextColor(Color.RED)
            binding.tvEmailMain.setTextColor(Color.RED)
            binding.tvLoginMain.text="LOGIN : "+uL.login
            binding.tvMainPwd.text="PASSWORD : "+uL.pwd
            binding.tvEmailMain.text="EMAIL : "+uL.email
        }
    }
    private fun ecrire() {
        val u = User(
            0, binding.etChildrenLogin.text.toString(),
            binding.etChildrenPwd.text.toString(),
            binding.etChildrenEmail.text.toString()
        )
        AsyncTask.execute{
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val u1 = UserRecord(0, u.login, u.pwd, u.email,)
            dao.insertUser(u1)
        }
        Toast.makeText(this,u.toString(),Toast.LENGTH_LONG).show()
    }
    private fun lire() {
        AsyncTask.execute {
            val db = Room.databaseBuilder(
                applicationContext,
                MyDB::class.java, "MyDataBase"
            ).build()
            val dao = db.userDao()
            val liste = dao.get()
            liste.forEach { item -> Log.i("READ", item.toString()) }
        }
    }
    private fun afficherSiteWeb(){
        val iWebSite = Intent(this,WebviewActivity::class.java)
        startActivity(iWebSite)
    }
}