package com.example.recruitment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(context: Context, persons: MutableList<Person>) :
    ArrayAdapter<Person>(context, R.layout.list_item, persons) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val nameTV = view?.findViewById<TextView>(R.id.nameTV)
        val surnameTV = view?.findViewById<TextView>(R.id.surnameTV)
        val ageTV = view?.findViewById<TextView>(R.id.ageTV)
        val roleTV = view?.findViewById<TextView>(R.id.roleTV)

        nameTV?.text = person?.name
        surnameTV?.text = person?.surname
        ageTV?.text = person?.age.toString()
        roleTV?.text = person?.role

        return view!!
    }

}