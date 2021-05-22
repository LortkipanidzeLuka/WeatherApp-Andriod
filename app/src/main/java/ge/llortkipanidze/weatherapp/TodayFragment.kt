package ge.llortkipanidze.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

class TodayFragment : Fragment() {
    lateinit var temperatureValueTextView :TextView
    lateinit var feelsLikeValueTextView: TextView
    lateinit var humidityValueTextView: TextView
    lateinit var pressureValueTextView: TextView
    lateinit var temperatureTextView: TextView
    lateinit var weatherDescriptionTextView: TextView
    lateinit var weatherImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.today_fragment, container, false)
        temperatureValueTextView = view.findViewById(R.id.temperatureValueTextView)
        feelsLikeValueTextView = view.findViewById(R.id.feelsLikeValueTextView)
        humidityValueTextView = view.findViewById(R.id.humidityValueTextView)
        pressureValueTextView = view.findViewById(R.id.pressureValueTextView)
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        weatherDescriptionTextView = view.findViewById(R.id.weatherDescriptionTextView)
        weatherImageView = view.findViewById(R.id.weatherImage)
        return view
    }

    fun renderData(model:TodayModel){
        temperatureTextView.text = model.main.temp.roundToInt().toString() + "°"
        weatherDescriptionTextView.text = model.weather[0].main
        temperatureValueTextView.text = model.main.temp.roundToInt().toString() + "°"
        feelsLikeValueTextView.text = model.main.feels_like.roundToInt().toString() + "°"
        humidityValueTextView.text = model.main.humidity.toString() + "%"
        pressureValueTextView.text = model.main.pressure.toString()

        val iconUrl = "https://openweathermap.org/img/wn/${model.weather[0].icon}@2x.png"
        Glide.with(this)
                .load(iconUrl)
                .circleCrop()
                .into(weatherImageView)

    }

}