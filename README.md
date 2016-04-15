#Moquette Broker Utilizing SSL/TLS

To run a generic unmolested version of the Moquette MQTT Broker download the Moquette distribution tar, untar the archive and run the script 'sh moquette.sh' from /bin. This starts an un-encrypted local broker listening on port 1883. Better yet check out the master branch and clone Andrea's [repo](https://github.com/andsel/moquette). This purpose of this repo is to display and share a working secured MQTT broker with encrypted coms. Feel free to clone the repo and run the broker from source or embedd in your Maven project following the directions below...


## Docmentation
### Configuration 
Within /config resides the moquette.conf file where we point moquette to...
* Keystore location and credentials (utilizing JKS by default)	
* SSL port (standard seems to be 8883)
* Authentication by username/pwrd is configured via config/password_file.conf
  * File should only contain user:sha256hash(password)  
  * Yes that hash below, and all passwords for that matter in the repo are password you'll need that to export the servers cert for your client and for connecting to the secure broker.
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

Embedding Moquette and starting the server in Eclipse...

* You can embed Moquette in your project by declearing the dependancies in Maven
* Or to edit and start from source clone the repo, open embedding_moquette, and compile and run the project from EmbeddedLauncher.java

All you really need to start...

```java
final IConfig classPathConfig = new ClasspathConfig();   //grab a new config object
final Server mqttBroker = new Server();                  //and a new broker
mqttBroker.startServer(classPathConfig);		         //start broker using settings
      							                         //from moquette.conf
``` 



* To connect to the broker from an Android Virtual Device be sure to utilize 10.0.0.2 as this is the address Android Studio binds to your dev machine's local host. More details on connecting clients, MQTT pub/sub, and org.eclipse.paho.android.service [here](http://gottaputthelinkhere.com).






