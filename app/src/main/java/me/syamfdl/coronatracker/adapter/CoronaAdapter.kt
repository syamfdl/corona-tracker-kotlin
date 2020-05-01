package me.syamfdl.coronatracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.syamfdl.coronatracker.R
import me.syamfdl.coronatracker.data.AttributesProvinsi
import me.syamfdl.coronatracker.data.DataProvinsi
import me.syamfdl.coronatracker.databinding.ProvinceListBinding

class CoronaAdapter (private val corona: List<DataProvinsi>) : RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder>(){

    inner class CoronaViewHolder (
        val recyclerviewCoronaBinding: ProvinceListBinding
    ) :RecyclerView.ViewHolder(recyclerviewCoronaBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CoronaViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.province_list, parent, false)
        )

    override fun getItemCount() = corona.size

    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.recyclerviewCoronaBinding.tvProvinsi.text = corona[position].attributes.Provinsi
        holder.recyclerviewCoronaBinding.tvProvPositif.text = corona[position].attributes.Kasus_Posi.toString()
        holder.recyclerviewCoronaBinding.tvProvSem.text = corona[position].attributes.Kasus_Semb.toString()
        holder.recyclerviewCoronaBinding.tvProvMen.text = corona[position].attributes.Kasus_Meni.toString()
    }

}