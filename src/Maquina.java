public class Maquina {
    private int RAM = 100;
    private int PROCESADORES = 16;
    private boolean recursos = true;

    public Maquina(){

    }
    public synchronized int getRam(){return RAM;}
    public synchronized int getProcesadores(){return PROCESADORES;}

    public synchronized boolean checkProcess(){return recursos;}

    public synchronized void esperar(){
        while(!recursos)
            try{
                System.out.println("Auch me he quedado sin recursos");
                wait();
            }catch (InterruptedException e){}

    }

    public synchronized void setRecursos(boolean recursos){this.recursos = recursos;}

    public synchronized void retProcesadores(int procesadoresOcupados){ PROCESADORES += procesadoresOcupados;}

    public synchronized void retRam(int ramOcupada){RAM += ramOcupada;}

    public synchronized void retAllRecursos(int ramOcupada, int procesadoresOcupados){
        RAM += ramOcupada;
        PROCESADORES += procesadoresOcupados;
        quienIngreso();
        notifyAll();
    }

    public synchronized void lessRecursos(int ramOcupada, int procesadoresOcupados){
        this.RAM -= ramOcupada;
        this.PROCESADORES -= procesadoresOcupados;
        checkProcess();
    }
    public synchronized void quienIngreso(){
        System.out.println("Procesadores restantes -> " + PROCESADORES);
        System.out.println("RAM restantes ->" + RAM);
    }
    public synchronized void abortarOperacion(){
        if(RAM == 0 || PROCESADORES == 0)
            recursos = false;
    }


}