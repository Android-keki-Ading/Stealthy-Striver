package com.mediading.stealthystriver.utils;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface MVUtilsEntryPoint {
    MVUtils getMVUtils();
}
