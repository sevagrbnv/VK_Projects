package com.example.vk_projects.presentation.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vk_projects.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var itemNumber: Int? = UNDEFINED_NUMBER

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        initData()
    }

    private fun initData() {
        val item = itemNumber?.let { viewModel.getItem(it) }
        if (item != null) {
            this.context?.let { Glide.with(it).load(item.icon_url).into(binding.detailLogo) }
            binding.detailName.text = item.name
            binding.detailDesc.text = item.description
            binding.detailUrl.text = item.service_url
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun parseParam() {
        val args = requireArguments()
        if (args.containsKey(NUMBER))
            itemNumber = args.getInt(NUMBER)
        else throw RuntimeException("Parameters not found")
    }

    companion object {

        private const val UNDEFINED_NUMBER = -1
        private const val NUMBER = "NUMBER"

        fun newInstanceFromRemote(number: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(NUMBER, number)
            }
        }
    }
}