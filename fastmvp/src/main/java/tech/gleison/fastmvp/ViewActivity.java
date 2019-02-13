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

import java.lang.reflect.ParameterizedType;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Visualizador da activity
 *
 * @param <Presenter> Apresentador
 * @author Gleison M. Vasconcelos
 * @version 1.0
 */
public abstract class ViewActivity<Presenter extends PresenterActivity> extends AppCompatActivity {

    /**
     * Apresentador
     */
    public Presenter presenter;

    /**
     * Inicializa o View
     */
    @SuppressWarnings("unchecked")
    public ViewActivity() {
        ParameterizedType type = (ParameterizedType) (getClass().getGenericSuperclass());
        if (type == null) return;
        try {
            Class<Presenter> aClass = (Class<Presenter>) type.getActualTypeArguments()[0];
            presenter = aClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        presenter.load();
        super.onStart();
    }

    @Override
    public void onStop() {
        presenter.stop();
        super.onStop();
    }
}