package com.example.path.dagger;

import com.example.path.viewmodel.MainActivityVM;

import dagger.Component;

@MainActivityVMScope
@Component(modules = {MainActivityVMModule.class}, dependencies = {PathApplicationComponent.class})
public interface MainActivityVMComponent {
    void inject(MainActivityVM mainActivityVM);
}
