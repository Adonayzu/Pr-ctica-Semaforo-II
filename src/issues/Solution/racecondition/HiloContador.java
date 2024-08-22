package issues.Solution.racecondition;

import issues.Solution.Semaforo.SemaforoBinario;

public class HiloContador implements Runnable {

    private final Contador contador;

    private final SemaforoBinario semaforoBinario; //igual se agrego una variable para que almacenelos datos

    //se agrego aqui tambien el semafprp binario
    public HiloContador(Contador contador, SemaforoBinario semaforoBinario) {
        this.contador = contador;
        this.semaforoBinario = semaforoBinario; //asignar a l a variable interna del semaforoBinario
    }

    @Override
    //aqui es donde se hace la ejecución del hilo
    public void run() {
        //Se agre el try y catch para que no de error la palabra acquire
        try{
            semaforoBinario.acquire(); // llamar al semaforo binario y asignar el acquire
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
            semaforoBinario.release(); //llamar el release para liberar
        }catch(InterruptedException ie){
            System.out.println("Sucedió un excepción interrumpida; " + ie.getMessage());
        }
    }
}
