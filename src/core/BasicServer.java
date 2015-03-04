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
package core;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoAPEndpoint;


/**
 * This is  a basic CoAP server.
 * @author Nikos Fotiou
 */
public class BasicServer extends CoapServer{
    private int port;
    
    /**
     * The default constructor.
     */
    public BasicServer(){
        
    }
    
    /**
     * Constructor with listening port.
     * @param port The port in which the server will listen when the listen() is called
     */
    public BasicServer (int port){
        this.port = port;
    }
    
    /**
     * Returns the listening port.
     * @return The port
     */
    public int getPort(){
        return port;
    }
    
    /**
     * It sets the listening port.
     * @param port The port
     */
    public void setPort(int port){
        this.port = port;
    }
    
    /**
     * It adds a new resource to the server.
     * @param resource The resource to be added
     */
    public void addResourse(CoapResource resource){
        add(resource);
    }
    
    /**
     * It listens for incoming requests.
     * @throws Exception If the listening port has not been set it throws an exception
     */
    public void listen() throws Exception{
        if (port == 0){
            throw new Exception("Port has not been set");
        }
        CoAPEndpoint endpoint = new CoAPEndpoint (port);
        this.addEndpoint(endpoint);
        this.start();
    }
    
    
}
