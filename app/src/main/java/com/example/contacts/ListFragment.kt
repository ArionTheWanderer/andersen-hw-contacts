package com.example.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.contacts.databinding.FragmentListBinding
import com.example.contacts.databinding.ItemContactBinding


class ListFragment : Fragment() {

    private val viewModel: ContactsViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            contacts.forEach { contact ->
                val itemBinding = ItemContactBinding.inflate(layoutInflater)
                itemBinding.itemName.text = contact.name
                itemBinding.itemSurname.text = contact.surname
                itemBinding.itemPhoneNumber.text = contact.phoneNumber
                Log.e("ListFragment", "Contact id: ${contact.id}")
                itemBinding.root.setOnClickListener {
                    Log.e("ListFragment", "Contact id to bundle: ${contact.id}")
                    setFragmentResult(SHOW_DETAILS_REQUEST_KEY, bundleOf(BUNDLE_KEY to contact.id))
                }
                binding.llContacts.addView(itemBinding.root)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SHOW_DETAILS_REQUEST_KEY = "SHOW_DETAILS_REQUEST_KEY"
        const val BUNDLE_KEY = "BUNDLE_KEY"
    }
}
