package br.com.fiap.eightshop.domain.usecases

import br.com.fiap.eightshop.domain.entity.Company
import br.com.fiap.eightshop.domain.repository.CompanyRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetCompaniesUseCase(
    private val companyRepository: CompanyRepository,
    private val scheduler: Scheduler
) {
    fun execute(forceUpdate: Boolean): Single<List<Company>> {
        return companyRepository.getCompanies(forceUpdate)
            .subscribeOn(scheduler)
    }
}
