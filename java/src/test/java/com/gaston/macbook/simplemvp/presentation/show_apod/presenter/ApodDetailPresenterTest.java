package com.gaston.macbook.simplemvp.presentation.show_apod.presenter;

import com.gaston.macbook.simplemvp.data.cache.ImageCacheImpl;
import com.gaston.macbook.simplemvp.domain.interactor.ApodDetailsInteractor;
import com.gaston.macbook.simplemvp.presentation.show_apod.ApodDetailContract;
import com.gaston.macbook.simplemvp.presentation.show_apod.model.Apod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class ApodDetailPresenterTest {

    private static Apod APOD_DATA = new Apod("Voyager 1 is a space probe launched by NASA on September 5, 1977.", "https://www.irishtimes.com/polopoly_fs/1.3253776.1512420154!/image/image.jpg_gen/derivatives/ratio_1x1_w1200/image.jpg"
            , "https://cdn.vox-cdn.com/thumbor/q86jN4kVUKyYeto_uj-0DRFJ9k8=/0x0:800x600/1200x800/filters:focal(336x236:464x364)/cdn.vox-cdn.com/uploads/chorus_image/image/57830225/PIA17036.0.jpg"
            , "Voyager 1");

    @Mock private ApodDetailContract.View view;
    @Mock private ApodDetailsInteractor interactor;
    @Mock ImageCacheImpl cache;
    @Captor ArgumentCaptor<ApodDetailsInteractor.onDetailsFetched> fetchDataCallbackCaptor;
    private ApodDetailPresenter presenter;

    @Before
    public void setupApodPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new ApodDetailPresenter(view, interactor, cache);
    }

    @Test
    public void should_fetchApodDataFromWeb() {
        presenter.fetchApodData();
        verify(interactor).getApodDataFromRemote(fetchDataCallbackCaptor.capture());
        fetchDataCallbackCaptor.getValue().onSuccess(APOD_DATA);
    }
}