package ge.llortkipanidze.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2

const val ACTIVE_GEORGIA = "Tbilisi"
const val ACTIVE_UK  = "London"
const val ACTIVE_JAMAICA = "Kingston"


const val ACTIVE_NOW = 0
const val ACTIVE_FORECAST = 1

class MainActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager2
    lateinit var todayTabButton : ImageButton
    lateinit var hourlyTabButton: ImageButton

    lateinit var georgiaFlagImageView : ImageView
    lateinit var ukFlagImageView : ImageView
    lateinit var jamaicaFlagImageView : ImageView

    lateinit var viewPagerAdapter: ViewPagerAdapter

    lateinit var cityNameTextView : TextView

    lateinit var backgroud : ConstraintLayout

    var activeCountry = ACTIVE_GEORGIA




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        viewpager = findViewById(R.id.viewPager)

        backgroud = findViewById(R.id.MainBackground)

        cityNameTextView = findViewById(R.id.CityNameTextView)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewpager.adapter = viewPagerAdapter

        setUpTabButtons()
        setUpFlags()

        viewPagerAdapter.loadData(ACTIVE_GEORGIA, ACTIVE_NOW, backgroud )



    }

    fun setUpTabButtons(){
        todayTabButton = findViewById(R.id.TodayTabButton)
        hourlyTabButton = findViewById(R.id.HourlyTabButton)

        todayTabButton.setOnClickListener { handleTabButtonTap(ACTIVE_NOW) }
        hourlyTabButton.setOnClickListener { handleTabButtonTap(ACTIVE_FORECAST)}

    }

    fun setUpFlags(){
        georgiaFlagImageView = findViewById(R.id.GeorgiaFlag)
        ukFlagImageView = findViewById(R.id.BritainFlag)
        jamaicaFlagImageView = findViewById(R.id.JamaicaFlag)

        georgiaFlagImageView.setOnClickListener{
            handleFlagTap(ACTIVE_GEORGIA)
        }

        ukFlagImageView.setOnClickListener{
            handleFlagTap(ACTIVE_UK)
        }

        jamaicaFlagImageView.setOnClickListener{
            handleFlagTap(ACTIVE_JAMAICA)
        }
    }

    fun handleTabButtonTap(tab: Int){

        viewpager.currentItem = tab
        viewPagerAdapter.loadData(activeCountry, tab, backgroud)
    }
    fun handleFlagTap(city: String){
        activeCountry = city
        cityNameTextView.text = city
        viewPagerAdapter.loadData(city, viewpager.currentItem, backgroud)
    }



}