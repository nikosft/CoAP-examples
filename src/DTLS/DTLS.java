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


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoAPEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;

/**
 * A basic Hello World example over DTLS. The server and the client are located in the 
 * same machine. This class contains the main method that creates the server and the
 * client. Use the GenKeys.sh to generate the the keystore and the truststore. In
 * this example only the server is authenticated, using a self-signed certificate.
 * @author fotiou
 */
public class DTLS {	

    //If you have modified GenKeys.sh modify the following variables accordingly
    private final static String KEY_STORE_PASSWORD = "123456";
    private static final String KEY_STORE_LOCATION = "keyStore.jks";
    private final static String TRUST_STORE_PASSWORD = "123456";
    private static final String TRUST_STORE_LOCATION = "trustStore.jks";

    public static void main(String[] args) {

            //prepapre server
            CoapServer server = new CoapServer(); 
            //create a new resource
            HelloWorldResource resource = new HelloWorldResource();
            //add the resource to the server
            server.add(resource);
            //prepare client
            CoapClient client = new CoapClient("coap://localhost:5684/helloWorld");
            //Creates a new handler
            HelloWorldHandler handler = new HelloWorldHandler();
            try {
                    //Here starts DTLS configuration of the server
                    // load the key store
                    KeyStore keyStore = KeyStore.getInstance("JKS");
                    InputStream in = new FileInputStream(KEY_STORE_LOCATION);
                    keyStore.load(in, KEY_STORE_PASSWORD.toCharArray());
                    DtlsConnectorConfig.Builder serverConfig = new DtlsConnectorConfig.Builder(new InetSocketAddress(5684));
                    serverConfig.setSupportedCipherSuites(new CipherSuite[]{
                                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8});
                    serverConfig.setIdentity((PrivateKey)keyStore.getKey("server", KEY_STORE_PASSWORD.toCharArray()),
                                    keyStore.getCertificateChain("server"), true);
                    serverConfig.setClientAuthenticationRequired(false);
                    DTLSConnector serverConnector = new DTLSConnector(serverConfig.build(), null);
                    server.addEndpoint(new CoAPEndpoint(serverConnector, NetworkConfig.getStandard()));
                    server.start();

                    //Here starts DTLS configuration of the client
                    //load the trust store
                    KeyStore trustStore = KeyStore.getInstance("JKS");
                    InputStream inTrust = new FileInputStream(TRUST_STORE_LOCATION);
                    trustStore.load(inTrust, TRUST_STORE_PASSWORD.toCharArray());
                    //load certificates
                    Certificate[] trustedCertificates = new Certificate[1];
                    trustedCertificates[0] = trustStore.getCertificate("server");
                    DtlsConnectorConfig.Builder clientConfig = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));
                    clientConfig.setTrustStore(trustedCertificates);
                    clientConfig.setSupportedCipherSuites(new CipherSuite[]{
                                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8});
                    DTLSConnector clientConnector = new DTLSConnector(clientConfig.build(), null);
                    client.setEndpoint(new CoAPEndpoint(clientConnector, NetworkConfig.getStandard()));
                    //start the client
                    client.get(handler);
                    //Just a trick to wait until the user press enter
                    System.out.println("Press enter to close the server and end the program");
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    br.readLine();
                    //Stop the server
                     server.destroy();

            } catch (Exception e) {
                     System.out.println("Exception " + e.toString());
                     e.printStackTrace();

            }


    }

}
