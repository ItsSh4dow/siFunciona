public class Proceso extends Thread{
    private int prioridad;
    private int procesadores;
    private int ram;
    private int time;
    private int idProceso;
    private boolean onProcess = false;
    private Maquina maquina;
    private int [] arrayRam = {10,20,30,40,50};
    private int [] times = {1,2,3};

    private int alet;

    Proceso(Maquina maquina, int idProceso){
        alet = (int) (Math.random() * 5);
        this.ram = arrayRam[alet];
        alet = (int) (Math.random() * 2);
        time = times[alet];
        this.prioridad = (int) (Math.random() * 3);
        this.procesadores = (int) (Math.random() * 8) + 1;
        this.maquina = maquina;
        this.idProceso = idProceso;
    }

    public synchronized int getRam(){
        return ram;
    }

    public synchronized boolean getOnProcess(){
        return onProcess;
    }

    public synchronized int getProcesadores(){
        return procesadores;
    }

    public synchronized int getTime(){
        return time;
    }

    public synchronized int getPrioridad(){
        return prioridad;
    }

    public synchronized int getIdProceso(){
        return idProceso;
    }

    public synchronized void setOnProcess(boolean onProcess){
        this.onProcess = onProcess;
    }

    private void endThread(){
        System.out.println("[!] Proceso "+idProceso+" ha terminado en " + time+ " minutos "
                +"\nSe ha liberado " + ram + " y " + procesadores + " procesadores");
        maquina.retAllRecursos(ram, procesadores);
    }
    @Override
    public void run(){
        try{
            sleep((long) time * 1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        endThread();
    }
}
