import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ShellSort {
    private ArrayList<Proceso> arrayOfProcesos;
    /*public ArrayList<Proceso> retPrioridades(){
        return arrayOfProcesos;
    }*/
    public ShellSort(ArrayList<Proceso> arrayOfProcesos){
        this.arrayOfProcesos = arrayOfProcesos;
    }

    public void sortArray(){
        divide(0, this.arrayOfProcesos.size()-1);
    }

    public void divide(int numIni, int numFin){
        //Divide los elementos de la lista en un único elemento
        if(numIni<numFin && (numFin-numIni)>=1){
            int mitad = (numFin+numIni)/2;
            divide(numIni,mitad);
            divide(mitad+1,numFin);

            //fusiona los elementos de la matriz de arriba en una matriz ordenada
            merge(numIni,mitad,numFin);
        }
    }

    public void merge(int numIni,int mitad, int numFin){
        ArrayList<Proceso> arregloFusionado = new ArrayList<Proceso>();
        int izq = numIni;
        int der = mitad+1;

        while(izq<=mitad && der<=numFin){
            if(arrayOfProcesos.get(izq).getPrioridad()<=arrayOfProcesos.get(der).getPrioridad()){
                arregloFusionado.add(arrayOfProcesos.get(izq));
                izq++;
            }else{
                arregloFusionado.add(arrayOfProcesos.get(der));
                der++;
            }
        }

        while(izq<=mitad){
            arregloFusionado.add(arrayOfProcesos.get(izq));
            izq++;
        }
        while(der<=numFin){
            arregloFusionado.add(arrayOfProcesos.get(der));
            der++;
        }
        int i= 0;
        int j = numIni;

        while(i<arregloFusionado.size()){
            arrayOfProcesos.set(j,arregloFusionado.get(i++));
            j++;
        }
    }
}