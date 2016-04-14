#Moquete Broker Utilizing SSL/TLS

To run a generic unmolested version of the Moquette MQTT Broker download the Moquette distribution tar, untar the archive and run the script 'sh moquette.sh' from /bin.
This starts an un-encrypted local broker listening on port 1883.


## Docmentation
### Configuration 
Within /config resides the moquette.conf file where we point moquette to...
* Keystore location and credentials (utilizing JKS by default)	
* SSL port (standard seems to be 8883)
* Authentication by username/pwrd is configured via config/password_file.conf
  * file should only contain user:sha256hash(password)
```
username:5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8
```
* Other configurations are possible within in /config like acl lists or setting the servers ip but for quick local SSL/TLS the above is all we need.

### Usage
Starting and connecting to the broker...
* cd to /bin and sh moquette.sh
* If Moquette starts up and you receive simmilar messages to the below on startup SSL on the Broker is configured!
```
430  [main] INFO  DefaultMoquetteSslContextCreator  - Starting SSL using keystore at serverkeystore.jks
431  [main] INFO  DefaultMoquetteSslContextCreator  - jks not found in bundled resources, try on the filesystem
431  [main] INFO  DefaultMoquetteSslContextCreator  - Using /Task 1/moquette/bin/serverkeystore.jks 
587  [main] INFO  NettyAcceptor  - Starting SSL on port 8883
588  [main] INFO  NettyAcceptor  - Server binded host: 0.0.0.0, port: 8883
```


* To connect to the broker from an Android Virtual Device be sure utilize 10.0.0.2 as this is the address Android Studio binds to your local host. More details on connecting clients, MQQT pub/sub, and android.paho.service [here](http://gottaputthelinkhere.com).




