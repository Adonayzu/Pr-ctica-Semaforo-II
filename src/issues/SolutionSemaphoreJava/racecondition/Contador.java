package issues.SolutionSemaphoreJava.racecondition;

public class Contador {

    private int contador = 0; // almacen la cuenta del contador

    public void incrementarContador(){ // este metodo incrementa el valor 1  a la vez
            contador ++;

        }

        public int getContador(){
        return contador;
        } // este metodo devuelve el valor actual del contador
}
