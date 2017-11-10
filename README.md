# QuijoteLui
Documentos Electrónicos SRI del Ecuador


Añadir el programa de Firma al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiFirmador/dist/QuijoteLuiFirmador-1.1.jar -DgroupId=com.quijotelui.firmador -DartifactId=QuijoteLuiFirmador -Dversion=1.1 -Dpackaging=jar

Añadir el programa de Cliente Web Service al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiClient/dist/QuijoteLuiClient-1.0.jar -DgroupId=com.quijotelui.clientews -DartifactId=QuijoteLuiClient -Dversion=1.0 -Dpackaging=jar

Añadir el programa de Reportes en PDF al repositorio de maven local:

$ mvn install:install-file -Dfile=/data/git/QuijoteLui/app/QuijoteLuiPrinter/dist/QuijoteLuiPrinter-1.0.jar -DgroupId=com.quijotelui.printer -DartifactId=QuijoteLuiPrinter -Dversion=1.0 -Dpackaging=jar