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

import java.lang.reflect.ParameterizedType;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity type viewer
 *
 * @param <Presenter> Presenter
 * @author Gleison M. Vasconcelos
 * @version 1.0.4
 */
public abstract class ViewActivity<Presenter extends PresenterActivity> extends AppCompatActivity {

    //
    // The Presenter
    //
    public Presenter presenter;

    @SuppressWarnings("unchecked")
    public ViewActivity() {
        ParameterizedType type = (ParameterizedType) (getClass().getGenericSuperclass());
        if (type == null) return;
        try {
            Class<Presenter> aClass = (Class<Presenter>) type.getActualTypeArguments()[0];
            this.presenter = aClass.newInstance();
            this.presenter.view = this;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method called after onResume
     */
    public void load() {
        Log.d(getClass().getSimpleName(), "ready");
    }

    @CallSuper
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.presenter.init();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        this.presenter.load();
        this.load();
    }

    @CallSuper
    @Override
    public void onStop() {
        this.presenter.stop();
        super.onStop();
    }
}