/*
 * Copyright (C) 2015 Nikos Fotiou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package DTLS;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;


/**
 * This is a basic resource. It can be accessed using the GET method and returns
 * the string ''Hello World''
 * @author Nikos Fotiou
 */
public class HelloWorldResource extends CoapResource {
        
    /**
     * The constructor
     */    
    public HelloWorldResource() {  
            // set resource identifier
            super("helloWorld");
        }
        
    /**
     * It is called when a GET method is received. 
     * @param exchange The user request
     */
    @Override
    public void handleGET(CoapExchange exchange) { 
       exchange.respond("Hello World!!");

    }
    }

