package com.example.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.contacts.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private val viewModel: ContactsViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var contactId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactId = it.getInt(ARG_CONTACT_ID)
            Log.e("DetailFragment", "Contact id from bundle: $contactId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            val filtered = contacts.filter { it.id == contactId }
            val contact = filtered.last()
            binding.detailsName.text = contact.name
            binding.detailsSurname.text = contact.surname
            binding.detailsPhoneNumber.text = contact.phoneNumber
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CONTACT_ID = "CONTACT_ID"
    }
}
