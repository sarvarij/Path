package com.example.path.geo_jawg;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.path.BuildConfig;
import com.example.path.models.JAWGFeatureCollection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class AutocompleteProvider {
    public interface Listener {
        void onAutocompleteReady(JAWGFeatureCollection jawgFeatureCollection);
    }

    private final Handler handler = new Handler();

    public class AutocompleteSubscription {

        private PublishSubject<String> publishSubject;

        private AutocompleteSubscription(Listener listener) {
            publishSubject = createPublishSubject(listener);
        }

        public void requestSearch(String text) {
            publishSubject.onNext(text);
        }
    }

    private Context context;
    private AutocompleteAPI autocompleteAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AutocompleteProvider(Context context, AutocompleteAPI autocompleteAPI) {
        this.context = context;
        this.autocompleteAPI = autocompleteAPI;
    }

    private void toast(String message) {
        handler.post(() ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }

    public AutocompleteSubscription subscribe(Listener listener){
        return new AutocompleteSubscription(listener);
    }

    public void dispose(){
        compositeDisposable.dispose();
    }

    private PublishSubject<String> createPublishSubject(Listener listener) {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject
                //.debounce(1200, TimeUnit.MILLISECONDS)
                .throttleLatest(1500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() >= 3;
                    }
                })
                .flatMapSingle(new Function<String, SingleSource<JAWGFeatureCollection>>() {
                    @Override
                    public SingleSource<JAWGFeatureCollection> apply(String s) {
                        return autocompleteAPI.getAutocomplete(BuildConfig.JAWG_API_KEY, "" + s, 47.4f, 19f)
                                .subscribeOn(Schedulers.io())
                                .onErrorReturn(throwable -> {
                                    toast("Error:" + throwable.getMessage());
                                    JAWGFeatureCollection result = new JAWGFeatureCollection();
                                    result.setFeatures(new ArrayList<>());
                                    return result;
                                });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FeatureCollectionObserver(listener));

        return publishSubject;
    }


    private class FeatureCollectionObserver implements Observer<JAWGFeatureCollection> {

        private Listener listener;

        private FeatureCollectionObserver(Listener listener) {
            this.listener = listener;
        }


        @Override
        public void onSubscribe(Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(JAWGFeatureCollection jawgFeatureCollection) {
            if (listener != null) {
                listener.onAutocompleteReady(jawgFeatureCollection);
            }

            if (jawgFeatureCollection.getFeatures().size() > 0) {
                toast("Retrieved: " + jawgFeatureCollection.getFeatures().size());
            }
        }

        @Override
        public void onError(Throwable e) {
            toast("Error");
        }

        @Override
        public void onComplete() {

        }
    }

    ;


}
