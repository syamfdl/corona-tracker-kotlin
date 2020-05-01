package me.syamfdl.coronatracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import me.syamfdl.coronatracker.R
import me.syamfdl.coronatracker.adapter.CoronaAdapter
import me.syamfdl.coronatracker.databinding.FragmentMainBinding
import me.syamfdl.coronatracker.databinding.FragmentProvinceBinding
import me.syamfdl.coronatracker.viewmodel.IndonesiaViewModel

/**
 * A simple [Fragment] subclass.
 */
class ProvinceFragment : Fragment() {

    private lateinit var binding: FragmentProvinceBinding
    private lateinit var viewModel: IndonesiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_province, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IndonesiaViewModel::class.java)

        binding.provinceviewmodel = viewModel

        viewModel.dataProvinsi.observe(viewLifecycleOwner, Observer {
            val adapter = CoronaAdapter(it)
            val recyclerView = binding.rvProvince
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        })
    }

}
