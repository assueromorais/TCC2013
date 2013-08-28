/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
/**
 * Classe criada para extender funcionalidades.
 * @author ASSUERO
 */
public class Data extends java.util.Date{
    
    /**
    * Retorna a diferença, em segundos, entre duas datas, a primeira deve ser menor do que a segunda para que .
    * @author ASSUERO
    */
    public static long DiferencaEmSegundos(Date data1, Date data2){
        long intDiferenca = 0;
        Calendar cldData1 = Calendar.getInstance();
        cldData1.setTime(data1);
        Calendar cldData2 = Calendar.getInstance();
        cldData2.setTime(data2);
        intDiferenca = cldData2.getTimeInMillis() - cldData1.getTimeInMillis();
        return intDiferenca / 1000;
    }
}
