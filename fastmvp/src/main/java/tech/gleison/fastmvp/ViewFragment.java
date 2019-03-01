/*
 * *************************************************************************************************
 *
 * Copyright 2019 Gleison M. Vasconcelos.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************************
 */
package tech.gleison.fastmvp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Fragment type viewer
 *
 * @param <Presenter> Presenter
 * @author Gleison M. Vasconcelos
 * @version 1.0.4
 */
public abstract class ViewFragment<Presenter extends PresenterFragment> extends Fragment {

    //
    // The Presenter
    //
    public Presenter presenter;

    //
    // is loaded ?
    //
    private boolean loaded = false;

    @SuppressWarnings("unchecked")
    public ViewFragment() {
        ParameterizedType type = (ParameterizedType) (getClass().getGenericSuperclass());
        if (type == null) return;
        try {
            Class<Presenter> aClass = (Class<Presenter>) type.getActualTypeArguments()[0];
            this.presenter = aClass.newInstance();
            this.presenter.view = this;
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inflate the layout from R
     *
     * @return layout
     */
    @LayoutRes
    public abstract int layout();


    /**
     * Called on activity is ready
     */
    public void ready() {
        Log.d(getClass().getSimpleName(), "ready");
    }

    /**
     * Initialize the components used the view
     *
     * @param view View
     */
    public void readyView(View view) {
        Log.d(getClass().getSimpleName(), "initialize");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(this.layout(), container, false);
        this.readyView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.getActivity() != null) {
            this.presenter.root = (ViewActivity) getActivity();
        }
    }

    /**
     * Check status of loading
     *
     * @return Boolean
     */
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.load();
        this.ready();
        this.loaded = true;
    }

    @Override
    public void onStop() {
        this.presenter.stop();
        this.loaded = false;
        super.onStop();
    }
}