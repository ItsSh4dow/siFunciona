import java.util.ArrayList;
public class Sistema {
    private ArrayList<Proceso> procesos = new ArrayList<>();
    private Maquina myPc = new Maquina();
    Sistema(Maquina maquina){
        this.myPc = maquina;
        int p = (int)(Math.random() * (20 - 2 + 1)) + 2;

        for (int i = 0; i < p; i++) {
            procesos.add(new Proceso(myPc,i));
        }
        ShellSort ms = new ShellSort(procesos);
        ms.sortArray();
    }
    private synchronized boolean checador(Proceso p){
        if(myPc.getRam()>=p.getRam() && myPc.getProcesadores()>= p.getProcesadores()) return true;
        return false;
    }
    private synchronized void ejecutarNuevoHilo(Proceso p){
        System.out.println("El proceso con Id: "+p.getIdProceso()+" está ejecutándose");
        myPc.lessRecursos(p.getRam(),p.getProcesadores());
        p.setOnProcess(true);
        p.start();
    }
    private class Kernel implements Runnable{
        int size = procesos.size();
        @Override
        public void run() {
            System.out.println("Tamaño de arreglo "+size+'\n');

            while(size>0){
                for (Proceso p : procesos) {
                    if(!myPc.checkProcess())break;
                    if(p.getOnProcess())continue;

                    if(checador(p)){
                        size--;
                        ejecutarNuevoHilo(p);
                    }
                }
                //espera si no hay recursos
                myPc.esperar();
            }
        }
    }

    public void ejecuta() {
        new Thread(new Kernel()).start();
    }
}
