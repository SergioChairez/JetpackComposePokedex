package com.plcoding.jetpackcomposepokedex.data.datasource.remote.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.plcoding.jetpackcomposepokedex.domain.models.VersionGroup

@Keep
internal data class FetchGenerationVersionResponseDTO(
    @SerializedName("version_groups") val versionGroups: List<VersionGroup>
)
