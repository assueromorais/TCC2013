/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cronometro;
import java.util.EventListener;
/**
 *
 * @author ASSUERO
 */
public interface CronometroOuvinte extends EventListener {
    public void IntervaloOcorreu(CronometroEvento evt);
}