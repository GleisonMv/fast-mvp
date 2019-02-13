package tech.gleison.example;

import tech.gleison.fastmvp.PresenterActivity;

public class MainPresenter extends PresenterActivity<MainActivity> {

    /**
     * Interface para implementar os métodos no View, Opcional
     *
     * Implements Main.Presenter na MainActivity
     */
    public interface View {

        void setHelloWorld(String text);
    }

    /**
     * Exemplo de método do presenter
     */
    public void loadTitle(){

        // Chama o método do View Implementado
        view.setHelloWorld("Hello World, Fast MVP!");
    }

    @Override
    public void load() {
        // Sua implementação não é obrigatória
        // Método para carregar o modelo(API, ou DB) se necessário
        // Trabalha no método onStart da activity
    }

    @Override
    public void stop() {
        // Sua implementação não é obrigatória
        // Método para descarregar o modelo(API, ou DB) se necessário
        // Trabalha no método onStop da activity
    }
}
