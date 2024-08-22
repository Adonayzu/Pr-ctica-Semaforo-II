package issues.Solution.Semaforo;

public class SemaforoBinario {

    private boolean control = true; // esta variable se utliza para poder controlar el estado del semaforo si es true o false
        //Método acquire ----adquirir

    //metodo  acquire
    public synchronized void acquire() throws InterruptedException { //sirve para adquirir el semaforo

        while(!control){ //!control significa que control esta em false, también se puede poner como false
            wait();
        }
        control = false; // este determina el que semaforo esta ocupado es false
    }

    //Método release ---> liberar
    public synchronized void release(){
        control = true;  // este determina que el semaforo esta desocupado
        notify();  // notifica a un hilo que esta esperando en el metodo de acquire para decirle que esta desocupado
    }
}
