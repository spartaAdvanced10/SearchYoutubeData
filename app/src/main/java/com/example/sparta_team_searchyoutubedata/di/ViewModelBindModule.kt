package com.example.sparta_team_searchyoutubedata.di

import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepository
import com.example.sparta_team_searchyoutubedata.network.data.repository.YoutubeDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelBindModule {

    @Binds
    abstract fun bindYoutubeDataRepository(
        repository: YoutubeDataRepositoryImpl
    ): YoutubeDataRepository
}