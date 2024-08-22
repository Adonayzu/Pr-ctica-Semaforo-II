package issues.SolutionSemaphoreJava.starvation;

import issues.Solution.Semaforo.SemaforoBinario;

import java.util.concurrent.Semaphore;

public class StarvationDemo {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1); // Se crea el semaforo con un permiso inical de 1 para que solo un hilo pueda acceder
        Thread hiloAltaPrioridad = new Thread(new TareaAltaPrioridad(semaforo), "Hilo alta prioridad") ;
        Thread hiloBajaPrioridad = new Thread(new TareaBajaPrioridad(semaforo), "Hilo baja prioridad" );

        hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);
        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

        hiloAltaPrioridad.start();
        hiloBajaPrioridad.start();

    }

    static class TareaAltaPrioridad implements Runnable {

        private final Semaphore semaforo; //Se crea la instancia y es privada ya que solo se utilizara para la clase TAreaAltaPrioridad

        //Crear un contructor con el mismo nombre de la clase
        // y se pasa el parametro de Semaforo
        public TareaAltaPrioridad(Semaphore semaforo) {
            this.semaforo= semaforo;    //Vincula con el semaforo de la variable de instancia y la del constructo
        }
        @Override
        public void run() {

            while(true) {
                System.out.println("Hilo de alta prioridad ejecutandose");

                try {
                    semaforo.acquire();
                    Thread.sleep(100);
                    semaforo.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class TareaBajaPrioridad implements Runnable {

        //Se crea la instancia y es privada para cada clase porque son clases diferentes
        private final Semaphore semaforo;

        //Crear un contructor con el mismo nombre de la clase
        // y se pasa el parametro de Semaforo
        public TareaBajaPrioridad(Semaphore semaforo) {

            this.semaforo = semaforo; //Vincula con el semaforo de la variable de instancia y la del constructor
        }
        @Override
        public void run() {
            while(true) {
                System.out.println("Hilo de baja prioridad ejecutandose");

                try {
                    semaforo.acquire(); // Sirve para adquirir un permiso del semaforo
                    Thread.sleep(1000);
                    semaforo.release();  // Sirve para poder liberar el permiso del semaforo
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}
