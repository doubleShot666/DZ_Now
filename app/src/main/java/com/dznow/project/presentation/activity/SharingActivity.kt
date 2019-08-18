package com.dznow.project.presentation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.dznow.project.R
import com.dznow.project.presentation.adapter.ContactAdapter
import com.dznow.project.presentation.model.Contact
import kotlinx.android.synthetic.main.sharing_activity_layout.*
import android.content.Intent
import android.net.Uri
import android.widget.Toast




class SharingActivity : AppCompatActivity() {

    lateinit var article_id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sharing_activity_layout)

        if(intent.getStringExtra("article_id") != null)
            article_id = intent.getStringExtra("article_id")

        showContactList()

        toolbar.setNavigationOnClickListener {
            this.finish()
        }

    }

    private fun showContactList() {
        if(getPermission()){
            val contacts = getContacts()

            val adapter = ContactAdapter(contacts,this,R.layout.contact_row_item,
                object : ContactAdapter.ContactAdapterListner {
                    override fun onContactClicked(contact: Contact) {
                        when(contact.type){
                            "email" -> sendEmail(article_id,contact.value)
                            "number" -> sendSMS(article_id,contact.value)
                        }
                    }
                })

            val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(contact_recyclerView.context, R.anim.layout_slide_from_side)
            contact_recyclerView.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            contact_recyclerView.itemAnimator = DefaultItemAnimator()
            contact_recyclerView.isNestedScrollingEnabled = false
            contact_recyclerView.adapter = adapter
            contact_recyclerView.layoutAnimation = controller
            contact_recyclerView.scheduleLayoutAnimation()


        }
    }

    private fun getContacts(): ArrayList<Contact>{
        val contacts = ArrayList<Contact>()

        val cr = contentResolver
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cur!!.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val cur1_email = cr.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                    arrayOf(id), null
                )
                while (cur1_email!!.moveToNext()) {
                    //to get the contact names
                    val name =
                        cur1_email.getString(cur1_email.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    Log.e("Name :", name)
                    val email = cur1_email.getString(cur1_email.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                    Log.e("Email", email)
                    if (email != null) {
                        contacts.add(Contact(name,email,"email"))
                    }
                }
                cur1_email.close()

                val curl_phones = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null
                )
                while (curl_phones!!.moveToNext()) {
                    val number = curl_phones.getString(curl_phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val name = curl_phones.getString(curl_phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    Log.e("Name :", name)
                    Log.e("Number: ",number)
                    contacts.add(Contact(name,number,"number"))
                }
                curl_phones.close()
            }
        }

        contacts.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name) })

        return contacts
    }

    private fun getPermission(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
        ) {
            true
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 3000)
            false
        }
    }

    private fun sendEmail(article_id : String,receiver : String){

        val uriText = "mailto:" + Uri.encode(receiver) +
                "?subject=" + Uri.encode("Partage d'article DZ Now") +
                "&body=" + Uri.encode(article_id)
        val uri = Uri.parse(uriText)
        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = uri

        try {
            startActivity(Intent.createChooser(sendIntent, "Send email"))
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(
                this@SharingActivity,
                "There is no email client installed.", Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun sendSMS(article_id: String,receiver: String){
        val uri = Uri.parse("smsto:$receiver")
        val it = Intent(Intent.ACTION_SENDTO, uri)
        it.putExtra("sms_body", article_id)


        try {
            startActivity(it)
            finish()
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(
                this@SharingActivity,
                "Error while sending SMS.", Toast.LENGTH_SHORT
            ).show()
        }
    }

}