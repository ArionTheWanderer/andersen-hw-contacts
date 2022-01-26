package com.example.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel: ViewModel() {
    private val _contacts = MutableLiveData(Contact.contacts)

    val contacts: LiveData<MutableList<Contact>>
        get() = _contacts
}
