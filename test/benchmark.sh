#!/bin/bash

runs=3

sizes="3_3_3 4_4_5 5_5_7"

strategies="DFS BFS ID_5 ID_100"

rm -r output
mkdir output

_pwd="`pwd`/output"

cd ..

for size in $sizes; do

    echo >> "${_pwd}/times.txt"
    echo "SIZE ${size}" | tr "_" " " >> "${_pwd}/times.txt"
    echo >> "${_pwd}/times.txt"

    echo >> "${_pwd}/states.txt"
    echo "SIZE ${size}" | tr "_" " " >> "${_pwd}/states.txt"
    echo >> "${_pwd}/states.txt"

    echo >> "${_pwd}/nodes.txt"
    echo "SIZE ${size}" | tr "_" " " >> "${_pwd}/nodes.txt"
    echo >> "${_pwd}/nodes.txt"

    for strategy in $strategies; do

        file="${_pwd}/${strategy}_${size}"
        echo "${strategy}: ${size}" | tr "_" " "

        # get data

        data_file="${file}.data"

        for ((i=0; i<$runs; i=i+1)); do
            ant run -Dargs="-r `echo ${size} | tr "_" " "` `echo ${strategy} | tr "_" " "`" | grep -E "\[java\]" >> ${data_file}
        done

        # generate tables

        grep -E "Tiempo" ${data_file} | grep -Eo "[0-9][0-9.]*" > ${file}_time.table
        grep -E "Estados" ${data_file} | grep -Eo "[0-9]+" > ${file}_states.table
        grep -E "expandidos:" ${data_file} | grep -Eo "[0-9]+" > ${file}_nodes.table

        f="${file}_time.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> "${_pwd}/times.txt"

        f="${file}_states.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> "${_pwd}/states.txt"

        f="${file}_nodes.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> "${_pwd}/nodes.txt"
    done
done

cd ${_pwd}

