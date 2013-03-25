#!/bin/bash

runs=5

sizes="3_3_3 4_4_5 5_5_7"

strategies="DFS BFS ID_5 ID_110"

mkdir output

_pwd="`pwd`/output"

cd ..

for size in $sizes; do

    table="${_pwd}/${size}"
    rm -f ${table}{_time,_states,_nodes}.txt

    for strategy in $strategies; do

        file="${_pwd}/${strategy}_${size}"
        echo "${strategy}: ${size}" | tr "_" " "

        # get data

        data_file="${file}.data"
        rm -f $data_file

        for ((i=0; i<$runs; i=i+1)); do
            ant run -Dargs="-r `echo ${size} | tr "_" " "` `echo ${strategy} | tr "_" " "`" | grep -E "\[java\]" >> ${data_file}
        done

        # generate tables

        grep -E "Tiempo" ${data_file} | grep -Eo "[0-9][0-9.]*" > ${file}_time.table
        grep -E "Estados" ${data_file} | grep -Eo "[0-9]+" > ${file}_states.table
        grep -E "expandidos:" ${data_file} | grep -Eo "[0-9]+" > ${file}_nodes.table

        f="${file}_time.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> ${table}_time.txt

        f="${file}_states.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> ${table}_states.txt

        f="${file}_nodes.table"
        echo "${strategy}: $(echo "scale=5 ; ($(cat "${f}" | tr "\n" "+")0) / $(cat "${f}" | wc -l)" | bc)" >> ${table}_nodes.txt
    done
done

cd ${_pwd}

