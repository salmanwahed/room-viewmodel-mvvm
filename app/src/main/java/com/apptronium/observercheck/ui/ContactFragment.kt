package com.apptronium.observercheck.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.apptronium.observercheck.R
import com.apptronium.observercheck.data.Contact
import kotlinx.android.synthetic.main.fragment_contact.*

private const val ARG_PARAM = "param"

class ContactFragment : Fragment() {
    private var param: String? = null
    private lateinit var mViewmodel: ContactViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
        mViewmodel = ContactViewmodel(contextArg = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_contact, container, false)

        val saveBtn = view.findViewById<Button>(R.id.btnSave)
        val deleteBtn = view.findViewById<Button>(R.id.btnDelete)
        val countText = view.findViewById<TextView>(R.id.tvContactCount)

        mViewmodel.getContactCount().observe(viewLifecycleOwner, Observer { count ->
            countText.text = "Total Contact: $count"
        })


        saveBtn.setOnClickListener {
            if (etName.text.isBlank()){
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_LONG).show()
            }else{
                val name = etName.text.toString()
                val contact = Contact(name = name)
                mViewmodel.insertContact(contact)
                Toast.makeText(context, "Saved new entry", Toast.LENGTH_SHORT).show()
                etName.text.clear()
            }
        }

        deleteBtn.setOnClickListener {
            mViewmodel.deleteLastContact()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}