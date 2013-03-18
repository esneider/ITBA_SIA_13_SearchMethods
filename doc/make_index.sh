#!/bin/bash

FILE_LIST="portada.mkd indice.mkd problema.mkd"

NAME="indice.mkd"

rm -f "${NAME}"

echo '## Índice' >> ${NAME}
echo >> ${NAME}

for file in ${FILE_LIST}; do

    title="$(echo $(tr a-z A-Z <<< "${file:0:1}")"${file:1}" | egrep -o "^[^.]+")"

    echo '* '"${title}" >> ${NAME}

    cat "${file}" | grep -E "^#.*# *$" | while read line; do

        title=$(echo "${line}" | grep -Eo "^#+" | tr "#" " ")
        title=${title:1}'* '"$(echo "${line}" | grep -Eo "[^# ].*[^# ]")"

        echo "${title}" >> ${NAME}
    done
done

