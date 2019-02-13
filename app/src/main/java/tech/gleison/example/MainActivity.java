package tech.gleison.example;

import android.os.Bundle;

import tech.gleison.fastmvp.ViewActivity;

/**
 * Activity Principal
 *
 * @author Gleison M. Vasconcelos
 * @version 1.0
 */
public class MainActivity extends ViewActivity<MainPresenter> implements MainPresenter.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setHelloWorld(String text) {

    }
}
