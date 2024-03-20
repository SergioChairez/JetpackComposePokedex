package com.plcoding.jetpackcomposepokedex.data.datasource.remote.api

import com.aallam.openai.client.OpenAI
import com.plcoding.jetpackcomposepokedex.BuildConfig
import javax.inject.Inject

class OpenAIServiceImpl @Inject constructor() : OpenAIService {
    override val openAI: OpenAI = OpenAI(BuildConfig.OPENAI_API_KEY)
}