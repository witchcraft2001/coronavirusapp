package ru.dmdev.coronavirusapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.coronavirusapiapp.adapters.CountryListAdapter
import ru.dmdev.coronavirusapiapp.databinding.ActivityMainBinding
import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CountryListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CoronaVirusApiApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView(binding.rvItems)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.refreshCountries()

        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                showList(it)
            }
        })
    }

    fun showList(countries: List<Country>) {
        adapter.add(countries)
    }

    fun initRecyclerView(rvItem: RecyclerView) {
        adapter = CountryListAdapter()
        rvItem.adapter = adapter
        rvItem.layoutManager = LinearLayoutManager(this)
        rvItem.setHasFixedSize(true)
    }
}
