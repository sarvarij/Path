package com.example.path.db;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EndpointStore {

    public interface Listener {
        void onEndpointLoaded(EndpointEntity endpointEntity);
    }

    private final EndpointDAO endpointDAO;
    private Listener listener;

    private class EndpointListObserver implements Observer<List<EndpointEntity>> {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<EndpointEntity> endpointEntities) {
            for (EndpointEntity endpointEntity : endpointEntities) {

                if (listener != null) {
                    listener.onEndpointLoaded(endpointEntity);
                }
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


    private class EndpointListCallable implements Callable<List<EndpointEntity>> {
        @Override
        public List<EndpointEntity> call() throws Exception {
            return endpointDAO.getAll();
        }
    }

    public void loadFromDB() {
        Observable.fromCallable(new EndpointListCallable())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EndpointListObserver());
    }

    public void saveToDB(EndpointEntity endpointEntity, String displayText) {
        endpointEntity.setLocationText(displayText);

        new Thread(() -> endpointDAO.insert(endpointEntity)).start();
    }


}
