package dev.sanmer.color.picker.di

import dev.sanmer.color.picker.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModels = module {
    viewModelOf(::MainViewModel)
}