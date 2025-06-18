package com.asparrin.carlos.laboratoriocalificado03.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asparrin.carlos.laboratoriocalificado03.R
import com.asparrin.carlos.laboratoriocalificado03.databinding.ActivityMainBinding
import com.asparrin.carlos.laboratoriocalificado03.data.model.TeachersResponse
import com.asparrin.carlos.laboratoriocalificado03.data.network.ApiClient
import com.asparrin.carlos.laboratoriocalificado03.ui.adapter.TeacherAdapter
import com.asparrin.carlos.laboratoriocalificado03.util.Constants
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        loadTeachers()
    }

    private fun setupRecycler() = with(binding.rvTeachers) {
        // GridLayout con 2 columnas
        layoutManager = GridLayoutManager(this@MainActivity, 2)
        // Adapter inicial con lista vacía
        adapter = TeacherAdapter(emptyList()).also { this@MainActivity.adapter = it }
        // Decoración para separar tarjetas
        val spacing = resources.getDimensionPixelSize(R.dimen.card_spacing)
        addItemDecoration(GridSpacingItemDecoration(2, spacing, true))
    }

    private fun hasInternetConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun loadTeachers() {
        if (!hasInternetConnection()) {
            Toast.makeText(this, getString(R.string.error_network), Toast.LENGTH_LONG).show()
            return
        }

        lifecycleScope.launch {
            try {
                val response = ApiClient.teacherService.getTeachers(Constants.TEACHERS_URL)
                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers.orEmpty()
                    Log.d("DEBUG_ADAPTER", "Recibidos ${teachers.size} docentes")
                    adapter.updateData(teachers)
                } else {
                    Log.e("DEBUG_ADAPTER", "Error HTTP ${response.code()}")
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_network),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Log.e("DEBUG_ADAPTER", "Excepción: ${e.message}", e)
                Toast.makeText(
                    this@MainActivity,
                    "Error de red: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
