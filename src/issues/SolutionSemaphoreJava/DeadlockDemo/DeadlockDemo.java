package issues.SolutionSemaphoreJava.DeadlockDemo;


import issues.Solution.Semaforo.SemaforoBinario;

import java.util.concurrent.Semaphore; // Se importo la clase de Semaphore

public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object ob1 = new Object();
        Object ob2 = new Object();
        Object ob3 = new Object();
        Semaphore semaforo = new Semaphore(1);   // Se crea el semaforo con un permiso inical de 1 para que solo un hilo pueda acceder

        /// se agrego el parametro de semaforo a cada hilo
        Thread t1 = new Thread(new SyncThread(ob1, ob2, semaforo), "hilo1");
        Thread t2 = new Thread(new SyncThread(ob2, ob3, semaforo), "hilo2");
        Thread t3 = new Thread(new SyncThread(ob3, ob1, semaforo), "hilo3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Finalizado");


    }
}
// ayuda a sincronizar dos objetor por medio de un semaforo en este caso el Semaphore de java
class SyncThread implements Runnable {

    public Object ob1;
    public Object ob2;
    private Semaphore semaforo; //Se crea la variable

    // constructor
    public SyncThread(Object ob1, Object ob2, Semaphore semaforo) { // Se agrego el parametro de Semaphore semaforo
        this.ob1 = ob1;
        this.ob2 = ob2;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " generando lock en " + ob1 );
        //Se agrego el try y catch para que no diera error el acquire
        try {
            semaforo.acquire();// Sirve para adquirir un permiso del semaforo
            synchronized (ob1) {
                System.out.println(name + " lock generado en " +  ob1);
                work();
                System.out.println(name + " generando lock en " + ob2);
                synchronized (ob2) {
                    System.out.println(name + " lock generado en " +  ob2);
                    work();
                }
                System.out.println(name + " lock liberado en " +  ob2);

            }
            semaforo.release();  // Sirve para poder liberar el permiso del semaforo
        }catch (InterruptedException ie){
            System.out.println("Sucedió un InterruptedException: " + ie.getMessage());
        }

        System.out.println(name + " lock liberado en " +  ob1 );
        System.out.println("Finalizó ejecución");


    }

    private void work(){
        try {
            Thread.sleep(7000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}


