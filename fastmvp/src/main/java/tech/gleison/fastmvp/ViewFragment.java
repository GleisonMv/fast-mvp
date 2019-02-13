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
 * Visualizador do Fragmento
 *
 * @param <Presenter> Apresentador
 * @author Gleison M. Vasconcelos
 * @version 1.0
 */
public abstract class ViewFragment<Presenter extends PresenterFragment> extends Fragment {

    /**
     * Apresentador
     */
    public Presenter presenter;

    /**
     * Inicializa o View
     */
    @SuppressWarnings("unchecked")
    public ViewFragment() {
        ParameterizedType type = (ParameterizedType) (getClass().getGenericSuperclass());
        if (type == null) return;
        try {
            Class<Presenter> aClass = (Class<Presenter>) type.getActualTypeArguments()[0];
            presenter = aClass.newInstance();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Layout que será definido no Fragmento
     *
     * @return layout
     */
    @LayoutRes
    public abstract int getViewLayout();

    /**
     * Método que deve ser usado, para trabalhar com os elementos que necessita da activity
     */
    public abstract void onLoad();

    /**
     * Inicializa os componentes que utiliza do View
     *
     * @param view View
     */
    public void initialize(View view) {
        Log.d(getClass().getSimpleName(), "Init");
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(getViewLayout(), container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getActivity() != null) {
            presenter.rootView = (ViewActivity) getActivity();
            presenter.rootPresenter = presenter.rootView.presenter;
        }
    }

    @Override
    public void onStart() {
        presenter.load();
        onLoad();
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }
}