package br.com.fiap.eightshop.domain.di

import br.com.fiap.eightshop.domain.usecases.GetCompaniesUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetCompaniesUseCase(
            companyRepository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)