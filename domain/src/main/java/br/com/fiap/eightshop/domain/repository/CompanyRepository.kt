package br.com.fiap.eightshop.domain.repository

import br.com.fiap.eightshop.domain.entity.Company
import io.reactivex.Single

interface CompanyRepository {
    fun getCompanies(forceUpdate: Boolean): Single<List<Company>>
}
