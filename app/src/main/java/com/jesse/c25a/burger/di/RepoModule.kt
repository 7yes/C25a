package com.jesse.c25a.burger.di

import com.jesse.c25a.burger.data.model.RepoBurgerImpl
import com.jesse.c25a.burger.domain.RepoBurge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoBurgerImpl): RepoBurge

}