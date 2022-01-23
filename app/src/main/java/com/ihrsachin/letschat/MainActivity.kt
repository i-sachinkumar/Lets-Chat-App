package com.ihrsachin.letschat



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import com.ihrsachin.letschat.databinding.ActivityMainBinding
import android.text.InputFilter

import android.text.Editable
import android.text.InputFilter.LengthFilter

import android.text.TextWatcher
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val DEFAULT_MSG_LENGTH_LIMIT = 1000
    public var userName: String = "Anonymous"
    private var name : String = "Anonymous"

    private lateinit var mFirebaseDatabase : FirebaseDatabase
    private lateinit var messageDatabaseReference: DatabaseReference
    private lateinit var mChildEventListener: ChildEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initializing database
        mFirebaseDatabase = FirebaseDatabase.getInstance()
        messageDatabaseReference = mFirebaseDatabase.reference.child("messages")


        binding.progressBar.visibility = GONE

        val messages : ArrayList<Message> = ArrayList()

        val mMessageAdapter = MessageAdapter(this, messages)
        binding.messageListView.adapter = mMessageAdapter


        binding.messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // enable send button only if some message is written after trimming
                binding.sendButton.isEnabled = (charSequence.toString().trim().isNotEmpty())
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        binding.messageEditText.filters = arrayOf<InputFilter>(LengthFilter(DEFAULT_MSG_LENGTH_LIMIT))

        binding.sendButton.setOnClickListener{
            //TODO "send action"
            val message = Message(binding.messageEditText.text.toString().trim(),name,"", userName)
            //mMessageAdapter.add(message)
            messageDatabaseReference.push().setValue(message)
            //clear the messageEditText
            binding.messageEditText.text.clear()
        }

        mChildEventListener  = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message : Message = snapshot.getValue(Message::class.java)!!
                mMessageAdapter.add(message)
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }
            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
        messageDatabaseReference.addChildEventListener(mChildEventListener)


    }
}