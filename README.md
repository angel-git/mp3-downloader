mp3-downloader
==============

Downloads songs  from http://www.goear.com/

It's a really simple project to download songs from goear.com


[INSTALLATION]
- you need to set up the configuration.properties under src/com/ags/mp3/ folder (to specify the folder of the downloaded files)
- you need to have maven installed. http://maven.apache.org/

[RUN]
- under the project folder run: mvn clean install
- the previous step will generate a bat/sh file under your project_location/target/appassembler/bin folder
- execute mp3.bat or mp3.sh
- follow the screen instructions


[KNOWN BUGS]
- some songs are download with strange characters, please review the name of the songs before burning in a CD.