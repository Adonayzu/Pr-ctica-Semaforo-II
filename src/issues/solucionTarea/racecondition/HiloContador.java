package issues.solucionTarea.racecondition;

import issues.racecondition.Contador;

public class HiloContador implements Runnable {

    private final Contador contador;

    public HiloContador(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementarContador();
        }

    }
}
