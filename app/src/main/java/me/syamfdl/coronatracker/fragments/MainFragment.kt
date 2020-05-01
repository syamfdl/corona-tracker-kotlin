package me.syamfdl.coronatracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import me.syamfdl.coronatracker.R
import me.syamfdl.coronatracker.databinding.FragmentMainBinding
import me.syamfdl.coronatracker.viewmodel.IndonesiaViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: IndonesiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        viewModel = ViewModelProviders.of(this).get(IndonesiaViewModel::class.java)

        binding.tvHariTanggal.text = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(Date())

        viewModel.data.observe({ lifecycle }, {
            binding.tvIndoPositif.text = it.positif
            binding.tvIndoSembuh.text = it.sembuh
            binding.tvIndoMeninggal.text = it.meninggal
        })

        viewModel.dataPositif.observe({lifecycle}, {
            binding.jmlPositif.text = it.value
        })

        viewModel.dataSembuh.observe({lifecycle}, {
            binding.jmlSembuh.text = it.value
        })

        viewModel.dataMeninggal.observe({lifecycle}, {
            binding.jmlMeninggal.text = it.value
        })

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
        }

        binding.btnDataProvinsi.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_provinceFragment)
        }

        return binding.root
    }

}
