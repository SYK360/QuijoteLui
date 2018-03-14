# QuijoteLui

Documentos Electrónicos SRI del Ecuador

Software REST API escrito en Kotlin y Java, Framework SpringBoot 

Añadir el programa de Firma al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiFirmador/dist/QuijoteLuiFirmador-1.2.jar -DgroupId=com.quijotelui.firmador -DartifactId=QuijoteLuiFirmador -Dversion=1.2 -Dpackaging=jar

Añadir el programa de Cliente Web Service al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiClient/dist/QuijoteLuiClient-1.2.jar -DgroupId=com.quijotelui.clientews -DartifactId=QuijoteLuiClient -Dversion=1.2 -Dpackaging=jar

Añadir el programa de Reportes en PDF al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiPrinter/dist/QuijoteLuiPrinter-1.1.jar -DgroupId=com.quijotelui.printer -DartifactId=QuijoteLuiPrinter -Dversion=1.1 -Dpackaging=jar


Añadir oracle jdbc

$ mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
