package com.k10tetry.kurrency.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.k10tetry.kurrency.di.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkUtils @Inject constructor(@ApplicationContext context: Context) :
    ConnectivityManager.NetworkCallback() {

    var networkRequest: NetworkRequest = NetworkRequest.Builder().build()
    var connectivityManager: ConnectivityManager
    private val _connectivity: MutableLiveData<Boolean> = MutableLiveData()
    val connectivity: LiveData<Boolean> = _connectivity

    init {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            connectivityManager.registerNetworkCallback(networkRequest, this)
        }

        var connection = false

        connectivityManager.allNetworks.forEach {
            connectivityManager.getNetworkCapabilities(it)?.let { network ->
                if (network.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    connection = true
                    return@forEach
                }
            }
        }

        _connectivity.postValue(connection)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        _connectivity.postValue(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        _connectivity.postValue(false)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        _connectivity.postValue(false)
    }
}