package com.ihrsachin.letschat

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class MessageAdapter(context : Context, messages : ArrayList<Message>) : ArrayAdapter<Message>(context, 0, messages){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val current : Message? = getItem(position)
        var listViewItem = convertView

        if (listViewItem == null) {
            listViewItem =
                LayoutInflater.from(context).inflate(R.layout.item_message_mine, parent, false)
        }

        val messageTextView: TextView = listViewItem!!.findViewById(R.id.messageTextView)
        messageTextView.text = current!!.getText()

        val nameTextView: TextView = listViewItem.findViewById(R.id.nameTextView)
        nameTextView.text = current.getName()

        val imageView: ImageView = listViewItem.findViewById(R.id.photoImageView)
        //TODO "populate image in imageView if any"



        if(!current.getUserName().equals(MainActivity().userName)) {
            val message_container: LinearLayout = listViewItem.findViewById(R.id.message_container)
            message_container.gravity = Gravity.START
            val message_background: LinearLayout =
                listViewItem.findViewById(R.id.message_background)
            message_background.setBackgroundResource(R.drawable.round_corner_others)
        }
        return listViewItem
    }
}