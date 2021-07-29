package br.com.fiap.eightshop.ui.company

import androidx.lifecycle.MutableLiveData
import br.com.fiap.eightshop.data.model.Company
import br.com.fiap.eightshop.domain.usecases.GetCompaniesUseCase
import br.com.fiap.eightshop.ui.company.viewmodel.BaseViewModel
import br.com.fiap.eightshop.ui.company.viewmodel.StateMachineSingle
import br.com.fiap.eightshop.ui.company.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class CompanyListViewModel(
    val useCase: GetCompaniesUseCase,
    val uiScheduler: Scheduler
): BaseViewModel() {

    val state = MutableLiveData<ViewState<List<Company>>>().apply {
        value = ViewState.Loading
    }

    fun getCompanies(forceUpdate: Boolean = false) {
        disposables += useCase.execute(forceUpdate = forceUpdate)
            .compose(StateMachineSingle())
            .observeOn(uiScheduler)
            .subscribe(
                {
                    //onSuccess
                    state.postValue(it)
                },
                {
                    //onError
                }
            )
    }
}
