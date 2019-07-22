package com.example.path.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.path.PathApplication;
import com.example.path.dagger.DaggerMainActivityVMComponent;
import com.example.path.dagger.MainActivityVMModule;

public class MainActivityVMFactory implements ViewModelProvider.Factory{

    private void inject(MainActivityVM mainActivityVM){
        DaggerMainActivityVMComponent.builder()
                .mainActivityVMModule(new MainActivityVMModule(mainActivityVM))
                .pathApplicationComponent(PathApplication.getPathApplicationComponent())
                .build()
                .inject(mainActivityVM);

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == MainActivityVM.class) {
            MainActivityVM mainActivityVM = new MainActivityVM();
            inject(mainActivityVM);
            return (T) mainActivityVM;
        }else{
            throw new RuntimeException();
        }

    }
}
