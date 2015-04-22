/*
 * Copyright (C) 2015 fotiou
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


import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

/**
 * This is a simple Handler. It prints the server's response or if an error occurs
 * it prints the error.
 * @author fotiou
 */
public class HelloWorldHandler implements CoapHandler{

    /**
     * It is called when the CoAP server responds with some content. It prints the content of the 
     * response.
     * @param response The response of the server
     */
    @Override
    public void onLoad(CoapResponse response) {
        String content = response.getResponseText();
	System.out.println("Received response from Server : " + content);
    }

    /**
     * It is called when an error occurs.
     */
    @Override
    public void onError() {
        System.out.println("Error");
    }
    
}
