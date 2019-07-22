package com.example.path.dagger;

import com.example.path.db.EndpointDAO;
import com.example.path.db.EndpointStore;
import com.example.path.view.EndpointSetting;
import com.example.path.viewmodel.MainActivityVM;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityVMModule {

    private MainActivityVM mainActivityVM;

    public MainActivityVMModule(MainActivityVM mainActivityVM){

        this.mainActivityVM = mainActivityVM;
    }

    @Provides
    @MainActivityVMScope
    public EndpointSetting provideEndpointSetting() {
        EndpointSetting endpointSetting = new EndpointSetting();
        endpointSetting.setListener(mainActivityVM);
        return endpointSetting;
    }

    @Provides
    @MainActivityVMScope
    public EndpointStore provideEndpointStore(EndpointDAO endpointDAO) {
        EndpointStore endpointStore = new EndpointStore(endpointDAO);
        endpointStore.setListener(mainActivityVM);
        return endpointStore;
    }


}
