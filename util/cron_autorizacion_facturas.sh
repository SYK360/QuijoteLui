#!/bin/bash 

fecha_inicial=$(date +"%Y-%m-16")
fecha_actual=$(date +"%Y-%m-%d")

echo "Fecha inicial: "$fecha_inicial
echo "Fecha actual:  "$fecha_actual

echo "curl http://localhost:9001/QuijoteLui/rest/v1/factura_autoriza/fechaInicio/$fecha_inicial/fechaFin/$fecha_actual"

#curl http://localhost:9001/rest/v1/factura_autoriza/fechaInicio/$fecha_actual/fechaFin/$fecha_actual

