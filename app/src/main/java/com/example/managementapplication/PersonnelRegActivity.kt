package com.example.managementapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

// ───────────────────────────────────── 수강생 등록화면 ─────────────────────────────────────

class PersonnelRegActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var btnReg : Button
    lateinit var edtName : EditText
    lateinit var edtAge : EditText
    lateinit var edtTel : EditText

    lateinit var rg_gender : RadioGroup
    lateinit var rb_female : RadioButton
    lateinit var rb_male : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personnel_reg)

        btnReg = findViewById<Button>(R.id.btnReg)
        edtName = findViewById<EditText>(R.id.edtName)
        edtAge = findViewById<EditText>(R.id.edtAge)
        edtTel = findViewById<EditText>(R.id.edtTel)

        rg_gender = findViewById<RadioGroup>(R.id.gender)
        rb_female = findViewById<RadioButton>(R.id.female)
        rb_male = findViewById<RadioButton>(R.id.male)

        dbManager = DBManager(this, "personnelDB", null, 1)

        btnReg.setOnClickListener {
            var str_name : String = edtName.text.toString()
            var str_age : String = edtAge.text.toString()
            var str_tel : String = edtTel.text.toString()

            var str_gender : String = ""

            if(rg_gender.checkedRadioButtonId == R.id.female){
                str_gender = rb_female.text.toString()
            }

            if(rg_gender.checkedRadioButtonId == R.id.male){
                str_gender = rb_male.text.toString()
            }

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO personnel VALUES ('" + str_name + "', '" + str_gender +"', "+ str_age +", '"+ str_tel+ "')")
            sqlitedb.close()

            // 인텐트를 사용하여 db로 데이터 넘겨주기
            val intent = Intent(this, PersonnelInfoActivity::class.java)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }
    }
    // ───────────────────────────────────── Menu ─────────────────────────────────────
    // 메뉴 생성 및 정의
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personnel_reg, menu)
        return true
    }
    // 메뉴 선택 시 동작 정의
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){

            R.id.action_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.action_list -> {
                val intent = Intent(this, PersonnelListActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}