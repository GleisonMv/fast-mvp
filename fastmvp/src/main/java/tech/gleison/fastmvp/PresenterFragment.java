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
 * The presenter for fragment
 *
 * @param <View>     View Fragment
 * @param <Activity> View Activity
 * @author Gleison M. Vasconcelos
 * @version 1.0.4
 */
public class PresenterFragment<View extends ViewFragment, Activity extends ViewActivity> {

    //
    // The View
    //
    public View view;

    //
    // The Activity View
    //
    public Activity root;

    /**
     * Called onStart of Fragment
     */
    public void load() {
        Log.d(getClass().getSimpleName(), "Load");
    }

    /**
     * Called onStop of Fragment
     */
    public void stop() {
        Log.d(getClass().getSimpleName(), "Stop");
    }
}