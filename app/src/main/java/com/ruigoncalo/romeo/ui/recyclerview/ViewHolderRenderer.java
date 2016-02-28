package com.ruigoncalo.romeo.ui.recyclerview;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public interface ViewHolderRenderer<T> {
    void render(T ad, int position);
}