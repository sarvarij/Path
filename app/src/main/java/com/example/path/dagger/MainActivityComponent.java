package com.example.path.dagger;

import com.example.path.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(modules = {MainActivityModule.class}, dependencies = {PathApplicationComponent.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
