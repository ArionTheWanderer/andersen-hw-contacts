package com.example.contacts

import android.view.View

class Contact
private constructor(var id: Int, val name: String, val surname: String, val phoneNumber: String) {

    companion object {
        val contacts = mutableListOf(
            Contact(
                View.generateViewId(),
                "John",
                "Doe",
                "88005553535"
            ),
            Contact(
                View.generateViewId(),
                "John",
                "Doe-Junior",
                "88005553536"
            ),
            Contact(
                View.generateViewId(),
                "Jane",
                "Doe",
                "1100037"
            ),
            Contact(
                View.generateViewId(),
                "smth",
                "smth",
                "2316431423"
            ),
            Contact(
                View.generateViewId(),
                "Who",
                "Me",
                "0000000000"
            )
        )
    }
}
