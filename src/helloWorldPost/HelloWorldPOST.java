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
package helloWorldPost;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoAPEndpoint;

/**
 * A basic Hello World POST example. The server and the client are located in the 
 * same machine. This class contains the main method that creates the server and the
 * client
 * @author Nikos Fotiou
 */
public class HelloWorldPOST {
    public static void main(String[] args){
        //prepapre server
        CoapServer server = new CoapServer(); 
        //create a new resource
        HelloWorldResource resource = new HelloWorldResource();
        //add the resource to the server
        server.add(resource);        
        //prepare client
        CoapClient client = new CoapClient("localhost:2000/helloWorld");
        //Creates a new handler
        helloworldGET.HelloWorldHandler handler = new helloworldGET.HelloWorldHandler();
        try{
            //The real action takes place here
            //start the server
            CoAPEndpoint endpoint = new CoAPEndpoint (2000);
            server.addEndpoint(endpoint);
            server.start();
            //start the client
            client.post(handler,"Hello World!", 0);
            //Just a trick to wait until the user press enter
            System.out.println("Press enter to close the server and end the program");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
            //Stop the server
            server.stop();
            server.destroy();
        }catch(Exception e){
            System.out.println("Exception " + e.toString());
        }
    }
}
