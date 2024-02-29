package ai.purple.network.retrofit

import ai.purple.network.BuildConfig
import ai.purple.network.PurpleNetworkDataSource
import ai.purple.network.model.Location
import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Purple Network API
 */
private interface RetrofitPurpleNetworkApi {
    @GET(value = "locations")
    suspend fun getLocations(): NetworkResponse<List<Location>>
}

private const val PURPLE_BASE_URL = BuildConfig.BACKEND_URL

/**
 * Wrapper for data provided from the [PURPLE_BASE_URL]
 */
@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

/**
 * [Retrofit] backed [PurpleNetworkDataSource]
 */
@Singleton
internal class RetrofitPurpleNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : PurpleNetworkDataSource {

    private val networkApi = trace("RetrofitPurpleNetwork") {
        Retrofit.Builder()
            .baseUrl(PURPLE_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitPurpleNetworkApi::class.java)
    }

    override suspend fun getLocations(): List<Location> =
        networkApi.getLocations().data
}