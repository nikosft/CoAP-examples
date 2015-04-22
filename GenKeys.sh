#!/bin/bash
echo Generating server public/private key and adding it to keystore 
keytool -genkeypair -keyalg EC -dname "cn=Nikos Fotiou, ou=AUEB, o=MMLAB, c=GR" -alias server -keypass 123456 -keystore keyStore.jks -storepass 123456 -validity 180 
echo Exporting server certificate 
keytool -exportcert -keystore keyStore.jks -alias server -storepass 123456 -file server.cer
echo Adding server certificate to truststore 
keytool -importcert -keystore trustStore.jks -alias server -storepass 123456 -file server.cer -noprompt
echo Deleteing server certificate
rm server.cer
