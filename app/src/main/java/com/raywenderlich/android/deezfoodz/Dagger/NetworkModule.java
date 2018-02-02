package com.raywenderlich.android.deezfoodz.Dagger;

import com.raywenderlich.android.deezfoodz.app.Constants;
import com.raywenderlich.android.deezfoodz.network.UsdaApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anand on 11/18/2017.
 */

@Module
public class NetworkModule {
    public static final String NAME_STRING_URL ="name_string_url";

    @Provides
    @Named(NAME_STRING_URL)
    String StringUrl(){
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory gsonConvertorFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_STRING_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    UsdaApi provideUsdaApi(Retrofit retrofit) {
        return retrofit.create(UsdaApi.class);
    }
}
