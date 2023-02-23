/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch01;

/**
 *
 * @author Oficina
 */
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class HelloWorld2 {

    static Logger logger = Logger.getLogger("ch01.HelloWorld2");

    static public void main(String[] args) {
        BasicConfigurator.configure(); 
        logger.debug("Hello world2.");
        
    }
}