package com.user.ingeweb.alcalist_mobile_wms_new.caseuses

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.*
import java.lang.reflect.Type

class MultiResponseConverterFactory(private val gson: Gson) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return MultiResponseConverter(gson, adapter)
    }

    private inner class MultiResponseConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<T>
    ) : Converter<ResponseBody, T> {

        override fun convert(responseBody: ResponseBody): T? {
            val jsonReader = gson.newJsonReader(responseBody.charStream())
            val response = adapter.read(jsonReader)

            if (responseBody.contentLength() == 0L) {
                responseBody.close()
                return null
            }

            return response
        }
    }

    companion object {
        fun create(): MultiResponseConverterFactory {
            return MultiResponseConverterFactory(Gson())
        }
    }
}