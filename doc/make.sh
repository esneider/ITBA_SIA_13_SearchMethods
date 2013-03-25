#!/bin/bash

gcc -I. -E -x c -o informe_completo.html.bak informe.html
cat informe_completo.html.bak | egrep -v "^#.*$" | sed "s/@/#/g" > informe_completo.html
rm informe_completo.html.bak
