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

import android.util.Log;

/**
 * Apresentador para Activity
 *
 * @param <View> Visualizador Activity
 * @author Gleison M. Vasconcelos
 * @version 1.0
 */
public abstract class PresenterActivity<View extends ViewActivity> {

    /**
     * Visualizador do tipo da Activity
     */
    public View view;

    /**
     * Chamado no onStart da activity
     */
    public void load() {
        Log.d(getClass().getSimpleName(), "Load");
    }

    /**
     * Chamado no onStop da Activity
     */
    public void stop() {
        Log.d(getClass().getSimpleName(), "Stop");
    }
}