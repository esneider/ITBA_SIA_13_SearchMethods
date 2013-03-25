#!/bin/bash

runs=2

sizes="4_4_5 6_6_8 10_10_15"

strategies="DFS BFS ID_5 ID_110"

_pwd=`pwd`

cd ..

for size in $sizes; do

    table="${_pwd}/${strategy}"

    for strategy in $strategies; do

        file="${_pwd}/${strategy}_${size}"
        echo "${strategy} ${size}" | tr "_" " "

        # get data

        data_file="${file}.data"
        rm -f $data_file

        for ((i=0; i<$runs; i=i+1)); do
            ant run -Dargs="-r `echo ${size} | tr "_" " "` `echo ${strategy} | tr "_" " "`" | grep -E "\[java\]" >> ${data_file}
        done

        # generate table

        _time="`grep -E "Tiempo" ${data_file} | grep -Eo "[0-9][0-9.]*"`"
        states="`grep -E "Estados" ${data_file} | grep -Eo "[0-9]+"`"
        nodes="`grep -E "expandidos:" ${data_file} | grep -Eo "[0-9]+"`"

        echo "${_time}" > ${file}_time.table
        echo "${states}" > ${file}_states.table
        echo "${nodes}" > ${file}_nodes.table

        echo "${strategy}: $(echo "scale=3 ; ($) / " | bc)" >
    done
done

cd ${_pwd}

