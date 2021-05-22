package ge.llortkipanidze.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*
import kotlin.math.roundToInt

class HourlyRecyclerViewAdapter(var dataList : MutableList<HourlyForecast>) : RecyclerView.Adapter<HourlyItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_list_item, parent, false)
        return HourlyItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: HourlyItemViewHolder, position: Int) {

        val data =  dataList[position]
        holder.dateTextView.text = getTimeInFormat(data.dt)
        holder.temperatureTextView.text = data.main.temp.roundToInt().toString() + "°"
        holder.descriptionTextView.text = data.weather[0].main
        val iconUrl = "https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png"
        Glide.with(holder.iconImageView.context)
            .load(iconUrl)
            .circleCrop()
            .into(holder.iconImageView)


    }

    fun renewList(newList: List<HourlyForecast>){
        dataList.removeAll(dataList)
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    private fun getTimeInFormat(date:Long): String{
        val time = Date(date * 1000)
        val calendar = Calendar.getInstance()
        calendar.time = time
        val hours = String.format("%02d", calendar[Calendar.HOUR_OF_DAY])
        val month = calendar[Calendar.MONTH]
        val monthNames = arrayOf(
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )
        val amPm =  if (calendar[Calendar.AM_PM] == Calendar.AM) "AM" else "PM"
        val dayOfMonth = String.format("%02d",calendar[Calendar.DAY_OF_MONTH])

        return  "${hours} ${amPm} ${dayOfMonth} ${monthNames[month]}"

    }


}

class HourlyItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
    val iconImageView = itemView.findViewById<ImageView>(R.id.HourlyWeatherImage)
    val temperatureTextView = itemView.findViewById<TextView>(R.id.HourlyTemperatureTextView)
    val descriptionTextView = itemView.findViewById<TextView>(R.id.HourlyWeatherDescriptionTextView)
}