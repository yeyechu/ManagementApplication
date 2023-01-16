package com.example.managementapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // ───────────────────────────────────── Menu ─────────────────────────────────────
    // 메뉴 생성 및 정의
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // 메뉴 선택 시 동작 정의
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){

            R.id.action_list -> {
                val intent = Intent(this, PersonnelListActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_reg -> {
                val intent = Intent(this, PersonnelRegActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // ────────────────────────────────────── !! ──────────────────────────────────────
}