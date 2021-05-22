package ge.llortkipanidze.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HourlyFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapter = HourlyRecyclerViewAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myView = inflater.inflate(R.layout.hourly_fragment, container, false)
        recyclerView = myView.findViewById(R.id.hourlyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        val decoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(resources.getDrawable(R.drawable.white_divider))
        recyclerView.addItemDecoration(decoration)
        recyclerView.adapter = adapter
        return myView


    }



    fun dataRenewed(model: ForecastModel) {
        adapter.renewList(model.list)
        adapter.notifyDataSetChanged()
    }

}