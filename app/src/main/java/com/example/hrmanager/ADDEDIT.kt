package com.example.hrmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.example.hrmanager.databinding.AddeditBinding

class ADDEDIT:Activity(), View.OnClickListener {

    private lateinit var binding: AddeditBinding
    // create DBHandler object
    private val dbh = DBHandler(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= AddeditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSave.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        val extras = intent.extras
        if (extras!=null){
            // read and assign id from intent
            val id:Int = extras.getInt("ID")
            // get contact based on id
            val person = dbh.getPerson(id)
            // edit text populate
            binding.etId.setText(person.id.toString())
            binding.etName.setText(person.name)
            binding.etMobile.setText(person.mobile)
            binding.etAddress.setText(person.address)
            binding.etEmail.setText(person.email)
            binding.etImageFile.setText(person.imageFile)
            binding.ivImage.setImageResource(this.resources.getIdentifier(person.imageFile,"drawable","com.example.hrmanager"))
        }
    }

    override fun onClick(btn: View) {
        // code to run for buttons
        when(btn.id){
            R.id.btnSave->{
                // save code
                // read value from ID and put - if no value
                val cid:Int = try {
                    binding.etId.text.toString().toInt()
                }
                catch (e:Exception){
                    0
                }
                //decide add or update
                if (cid==0){
                    addContact()
                }else{
                    editContact(cid)
                }
            }
            R.id.btnCancel->{
                // cancel code
                goBack()
            }
        }
    }

    private fun editContact(cid: Int) {
        // create contact object and fill new values
        val person = dbh.getPerson(cid)
        person.name = binding.etName.text.toString()
        person.mobile = binding.etMobile.text.toString()
        person.address = binding.etAddress.text.toString()
        person.email = binding.etEmail.text.toString()
        person.imageFile = binding.etImageFile.text.toString()
        // call dbHandler update function
        dbh.updatePerson(person)
        // display confirmation and go to Main activity
        Toast.makeText(this,"Person has been updated",Toast.LENGTH_LONG).show()
        goBack()

    }

    private fun addContact() {
        // read values from edit text and assign to contact object
        val person = Person()
        person.name = binding.etName.text.toString()
        person.mobile = binding.etMobile.text.toString()
        person.address = binding.etAddress.text.toString()
        person.email = binding.etEmail.text.toString()
        person.imageFile = binding.etImageFile.text.toString()
        // call dbhandler function to add
        dbh.addPerson(person)
        Toast.makeText(this,"New Person Created", Toast.LENGTH_LONG).show()
        goBack()

    }
    private fun goBack(){
        // go back to main activity
        val mainAct = Intent(this,MainActivity::class.java)
        // to start another activity
        startActivity(mainAct)

    }
}
