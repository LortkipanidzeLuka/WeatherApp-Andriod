package ge.llortkipanidze.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(@Query("q") q: String, @Query("appid") appid: String,
                          @Query("units") units: String) : Call<TodayModel>

    @GET("data/2.5/forecast")
    fun getHourlyForecast(@Query("q") q: String, @Query("appid") appid: String,
                          @Query("units") units: String) : Call<ForecastModel>
}