package com.example.recruitment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var persons: MutableList<Person> = mutableListOf()
    private val role = mutableListOf(
        "Менеджер",
        "Кассир",
        "Продавец",
        "Директор",
        "Бухгалтер"
    )

    private lateinit var toolbarMain: Toolbar
    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var ageET: EditText
    private lateinit var roleSpinner: Spinner
    private lateinit var saveBTN: Button
    private lateinit var personsLV: ListView

    var listAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Подбор персонала"
        toolbarMain.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        ageET = findViewById(R.id.ageET)
        roleSpinner = findViewById(R.id.roleSpinner)
        saveBTN = findViewById(R.id.saveBTN)
        personsLV = findViewById(R.id.personsLV)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, role)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roleSpinner.adapter = adapter

        saveBTN.setOnClickListener {
            if (nameET.text.isEmpty() || surnameET.text.isEmpty() || ageET.text.isEmpty()) return@setOnClickListener
            val name = nameET.text.toString()
            val surname = surnameET.text.toString()
            val age = ageET.text.toString().toInt()
            val role = roleSpinner.selectedItem.toString()
            val person = Person(name, surname, age, role)
            persons.add(person)
            listAdapter =  ListAdapter(this, persons)
            personsLV.adapter = listAdapter
            listAdapter?.notifyDataSetChanged()
            nameET.text.clear()
            surnameET.text.clear()
            ageET.text.clear()
        }

        personsLV.setOnItemClickListener { parent, view, position, id ->
            val selectedPerson = listAdapter!!.getItem(position)
            persons.removeAt(position)
            listAdapter!!.notifyDataSetChanged()
            Toast.makeText(this, "Работник ${selectedPerson?.name} удален", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}