package ge.llortkipanidze.weatherapp

import android.util.LayoutDirection
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val apiKey = "34d984960939fb28a27663341f19fa90"


class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    var todayFragment = TodayFragment()
    var hourlyFragment = HourlyFragment()


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 1){
            return hourlyFragment
        }
        return todayFragment
    }

    fun loadData(city : String, activePage: Int, backgroundLayout :ConstraintLayout){

        if (activePage == ACTIVE_NOW){
            loadTodayData(city, backgroundLayout)
        }
        else{
            loadForecastData(city)
        }


    }

    fun loadTodayData(city: String, backgroundLayout: ConstraintLayout){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        var weatherApi = retrofit.create(WeatherApi::class.java)

        var todayWeather = weatherApi.getCurrentWeather(city, apiKey, "metric" )

        todayWeather.enqueue(object : Callback<TodayModel> {
            override fun onFailure(call: Call<TodayModel>, t: Throwable) {
                Toast.makeText(todayFragment.context, "error during api request", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TodayModel>, response: Response<TodayModel>) {
                if(response.isSuccessful){
                    val day =  response.body()?.dt?.let { isDay(it) }
                    if (day!!){
                        backgroundLayout.setBackgroundColor(
                            todayFragment.getResources().getColor(R.color.appBackgroundColorDay))
                    }else{
                        backgroundLayout.setBackgroundColor(
                            todayFragment.getResources().getColor(R.color.appBackgroundColorNight))
                    }
                    response.body()?.let { todayFragment.renderData(it) }
                }
            }

        })
    }
    fun loadForecastData(city: String){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        var weatherApi = retrofit.create(WeatherApi::class.java)

        var forecast= weatherApi.getHourlyForecast(city, apiKey, "metric")

        forecast.enqueue(object : Callback<ForecastModel> {
            override fun onFailure(call: Call<ForecastModel>, t: Throwable) {
                Toast.makeText(hourlyFragment.context, "error during api request", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ForecastModel>, response: Response<ForecastModel>) {
                if(response.isSuccessful){
                    response.body()?.let { hourlyFragment.dataRenewed(it) }
                }
            }

        })

    }

    fun isDay(time: Long): Boolean{
        val time = Date(time * 1000)
        val calendar = Calendar.getInstance()
        calendar.time = time
        val hours = calendar[Calendar.HOUR_OF_DAY]
        if (hours in 6..18){
            return true
        }
        return false
    }



}

