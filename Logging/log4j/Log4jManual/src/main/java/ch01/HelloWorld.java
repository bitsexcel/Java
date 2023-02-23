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
import org.apache.log4j.Logger;

public class HelloWorld {

    static Logger logger = Logger.getLogger("ch01.HelloWorld");

    static public void main(String[] args) {
        logger.debug("Hello world.");
        
    }
}
