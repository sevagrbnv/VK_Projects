package com.example.vk_projects.presentation.mainFragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vk_projects.databinding.FragmentMainBinding
import com.example.vk_projects.presentation.mainFragment.rcView.DataAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private var openSecondFragmentListener: OpenSecondFragmentListener? = null

    private lateinit var listAdapter: DataAdapter

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OpenSecondFragmentListener) {
            openSecondFragmentListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        openSecondFragmentListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isOnline(requireContext())) {
            viewModel = ViewModelProvider(this)[MainViewModel::class.java]
            viewModel.dataLD.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
            setRecView()
        } else Toast.makeText(requireContext(), "No connection", Toast.LENGTH_LONG).show()
    }

    private fun setRecView() {
        listAdapter = DataAdapter()
        with(binding.rcView) {
            adapter = listAdapter
        }
        setItemClickListener()
    }

    fun setItemClickListener() {
        listAdapter.onItemClickListener = { itemClicked ->
            itemClicked?.let { item->
                viewModel.dataLD.value?.indexOf(item)
                    ?.let { it -> openSecondFragmentListener?.openSecondFragment(it) }
            }
        }
    }

    interface OpenSecondFragmentListener {
        fun openSecondFragment(number: Int)
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return capabilities != null
        }
        return false
    }

}