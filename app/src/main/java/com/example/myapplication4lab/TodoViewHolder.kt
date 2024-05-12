package com.example.myapplication4lab

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoViewHolder(view: View):ViewHolder(view) {
    val checkBox2:CheckBox = view.findViewById(R.id.cbTodo)
    val ivDelete:ImageView = view.findViewById(R.id.ivDelete)
    val ivEdit:ImageView=view.findViewById(R.id.btnedit)
}