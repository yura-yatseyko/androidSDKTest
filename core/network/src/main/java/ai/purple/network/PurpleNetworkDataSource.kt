package ai.purple.network

import ai.purple.network.model.Location

/**
 * Interface representing network calls to the Purple backend
 */
interface PurpleNetworkDataSource {
    suspend fun getLocations(): List<Location>
}
