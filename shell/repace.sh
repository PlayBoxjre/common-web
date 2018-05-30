#!/bin/bash
# 替换目录下所有文件内的指定字符


for v in `grep $1 -rl .`
do
	echo $v
	sed -E  -i ".bak"  "s/${1}/${2}/g" $v
done
    
