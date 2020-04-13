package ru.dmdev.coronavirusapiapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ru.dmdev.coronavirusapiapp.adapters.CountryListAdapter
import ru.dmdev.coronavirusapiapp.api.CoronavirusApi
import ru.dmdev.coronavirusapiapp.api.responses.CountryResponse
import ru.dmdev.coronavirusapiapp.extensions.toModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var adapter: CountryListAdapter

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private lateinit var apiService: CoronavirusApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.VISIBLE
        textNotFound.visibility = View.GONE
        rvItems.visibility = View.GONE
        apiService = CoronavirusApi.create()
        adapter = CountryListAdapter()
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)

        loadData()
    }

    private fun loadData() {
        uiScope.launch {
            Log.w(TAG, "load data")
            try {
                var response = withContext(ioScope.coroutineContext) { getSummaryData() }
                response.forEach {
                    adapter.add(it.toModel())
                }
                Log.w(TAG, "loaded data")
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
            if (adapter.itemCount == 0) {
                rvItems.visibility = View.GONE
                textNotFound.visibility = View.VISIBLE
            } else {
                rvItems.visibility = View.VISIBLE
                textNotFound.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        }
    }

    private suspend fun getSummaryData(): List<CountryResponse> {
        val response = apiService.getSummary();
        return response.countries
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }
}
