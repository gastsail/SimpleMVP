package com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.presenter

import com.gaston.macbook.kotlinsimplemvp.base.BasePresenter
import com.gaston.macbook.kotlinsimplemvp.data.cache.ImageCacheImpl
import com.gaston.macbook.kotlinsimplemvp.domain.interactor.ApodDetailsInteractor
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.ApodDetailContract
import com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model.Apod

class ApodDetailPresenter constructor(view: ApodDetailContract.View, apodInteractor: ApodDetailsInteractor, cache: ImageCacheImpl) : BasePresenter(), ApodDetailContract.Presenter {

    var view: ApodDetailContract.View? = null
    var apodInteractor: ApodDetailsInteractor? = null
    var cache: ImageCacheImpl? = null

    init {
        this.view = view
        this.apodInteractor = apodInteractor
        this.cache = cache
    }

    override fun fetchApodData() {
        view?.hideApod()
        view?.showProgressBar()
        apodInteractor?.getApodDataFromRemote(object : ApodDetailsInteractor.onDetailsFetched {
            override fun onSuccess(apodFetchedData: Apod) {
                view?.hideProgressBar()
                view?.showApod()
                view?.showApodDetails(apodFetchedData)
                cache?.saveHdUrl(apodFetchedData.hdurl)
            }

            override fun onFailure() {
                view?.showDataFetchError()
                view?.hideProgressBar()
            }
        })
    }

}