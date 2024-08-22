package issues.SolutionSemaphoreJava.racecondition;

import issues.Solution.Semaforo.SemaforoBinario;

import java.util.concurrent.Semaphore;

public class HiloContador implements Runnable {

    private final Contador contador;

    private final Semaphore semaforo; //igual se agrego una variable

    //se agrego aqui tambien el semaforo
    //constructor
    public HiloContador(Contador contador, Semaphore semaforo) {
        this.contador = contador;
        this.semaforo = semaforo; //asignar a l a variable interna del semaforo
    }

    @Override
    //aqui es donde se hace la ejecución del hilo
    public void run() {
        //Se agre el try y catch para que no de error la palabra acquire
        try{
            semaforo.acquire(); // llamar al semaforo y asignar el acquire adquiriendo el permiso del semaforo
            for (int i = 0; i < 1000; i++) {
                contador.incrementarContador();
            }
            semaforo.release(); //llamar el release para liberar el permiso del semaforo
        }catch(InterruptedException ie){
            System.out.println("Sucedió un excepción interrumpida; " + ie.getMessage());
        }
    }
}
