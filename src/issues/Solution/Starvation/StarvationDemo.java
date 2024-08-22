package issues.Solution.Starvation;

import issues.Solution.Semaforo.SemaforoBinario;

public class StarvationDemo {
    public static void main(String[] args) {
        SemaforoBinario semaforoBinario = new SemaforoBinario();
        Thread hiloAltaPrioridad = new Thread(new TareaAltaPrioridad(semaforoBinario), "Hilo alta prioridad") ;
        Thread hiloBajaPrioridad = new Thread(new TareaBajaPrioridad(semaforoBinario), "Hilo baja prioridad" );



      hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);
      hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

      hiloAltaPrioridad.start();
      hiloBajaPrioridad.start();


    }

    static class TareaAltaPrioridad implements Runnable {

        private final SemaforoBinario semaforoBinario; //Se crea la instancia y es privada y el final porque el semaforoBinario no se va a modificar y no se movera

        //Crear un contructor con el mismo nombre de la clase
        // y se pasa el parametro de SemaforoBinario
        public TareaAltaPrioridad(SemaforoBinario semaforoBinario) {
            this.semaforoBinario = semaforoBinario;    //Vincular con el semaforoBinario
        }
        @Override
        public void run() {

            while(true) {
                System.out.println("Hilo de alta prioridad ejecutandose");

                try {
                    semaforoBinario.acquire();
                    Thread.sleep(100);
                    semaforoBinario.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    static class TareaBajaPrioridad implements Runnable {

        //Se crea la instancia y es privada para cada clase porque son clases diferentes
        private final SemaforoBinario semaforoBinario;

        //Crear un contructor con el mismo nombre de la clase
        // y se pasa el parametro de SemaforoBinario
        public TareaBajaPrioridad(SemaforoBinario semaforoBinario) {
            this.semaforoBinario = semaforoBinario; //Vincular con el semaforoBinario
        }
        @Override
        public void run() {
            while(true) {
                System.out.println("Hilo de baja prioridad ejecutandose");

                try {
                    semaforoBinario.acquire();
                    Thread.sleep(1000);
                    semaforoBinario.release();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}
