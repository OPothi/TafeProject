package com.example.hrmanager

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.inline.InlineContentView
import java.net.Inet4Address

//class MyAdapter
class CustomAdapter (private val context:Context,
        private val arrayList:ArrayList<Person>) : BaseAdapter(){
    private lateinit var txtName: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtMobile: TextView
    private lateinit var txtEmail: TextView
    private lateinit var ivImage: ImageView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?,parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        txtName = convertView.findViewById(R.id.name)
        txtAddress = convertView.findViewById(R.id.address)
        txtEmail = convertView.findViewById(R.id.email)
        txtMobile = convertView.findViewById(R.id.mobile)
        ivImage = convertView.findViewById(R.id.imageFile)
        txtName.text = arrayList[position].name
        txtAddress.text = arrayList[position].address
        txtMobile.text = arrayList[position].mobile
        txtEmail.text = arrayList[position].email

        ivImage.setImageResource(context.resources.getIdentifier(arrayList[position].imageFile, "drawable","com.example.hrmanager"))
        return convertView

    }

}