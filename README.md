#Moquete Broker utilizing SSL/TLS

To run a generic unmolested version of the Moquette MQTT Broker download the Moquette distribution tar, untar the archive and run the script 'sh bin/moquette.sh'
this starts an un-encrypted local broker listening on port 1883.


## Docmentation
### Usage
Within /config resides the moquette.conf file where we point moquette to...
* Keystore location and credentials	
* SSL port (standard seems to be 8883)
* Authentication by username/pwrd is configured via config/password_file.conf
..* file should containe username:5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8
..* 					^^^sha256hash of your plain-text password



