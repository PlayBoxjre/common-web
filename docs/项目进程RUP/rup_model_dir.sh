#!/bin/bash

# 生成RUP的过程中的所要的目录


TARGET_DIR=./rup



# 创建业务建模目录
BUSINESS_DIR=${TARGET_DIR}/业务建模/
BUSINESS_CASE_DIR=${BUSINESS_DIR}/业务用例/
BUSINESS_OBJ_DIR=${BUSINESS_DIR}/业务对象/


mkdir -p ${BUSINESS_CASE_DIR}
mkdir -p ${BUSINESS_OBJ_DIR}
# 1. 愿景
WANT_FILE=${BUSINESS_DIR}wants.txt
touch $WANT_FILE
echo '系统名(SYSTEM):' >> ${WANT_FILE}
echo '-->' >> ${WANT_FILE}
echo '老大(boss):' >> ${WANT_FILE}
echo '-->' >> ${WANT_FILE}
echo '目标组织' >> ${WANT_FILE}
echo '' >> $WANT_FILE
echo '目标(量化)' >> $WANT_FILE
echo ''

# 需求
REQUIREMENTS_DIR=${TARGET_DIR}/需求/
REQUIREMENTS_SYS_CASE_DIR=${REQUIREMENTS_DIR}/系统用例/

mkdir -p ${REQUIREMENTS_SYS_CASE_DIR}

# 分析
ANASYS_DIR=${TARGET_DIR}/分析/
ANASYS_ENTITY_DIR=${ANASYS_DIR}/实体类/
ANASYS_CONTROLLER_DIR=${ANASYS_DIR}/控制类/
ANASYS_BOUND_DIR=${ANASYS_DIR}/边界类/
ANASYS_CASE_IMPLEMENTS_DIR=${ANASYS_DIR}/用例实现/

mkdir -p $ANASYS_CASE_IMPLEMENTS_DIR
mkdir -p $ANASYS_BOUND_DIR
mkdir -p $ANASYS_CONTROLLER_DIR
mkdir -p $ANASYS_ENTITY_DIR

# 设计
DESIGN_DIR=${TARGET_DIR}/设计/
DESIGN_VIEW_DIR=${DESIGN_DIR}/表现层/
DESIGN_DOMAN_DIR=${DESIGN_DIR}/领域层/
DESIGN_APPLICATION_DIR=${DESIGN_DIR}/应用层/
DESIGN_BASE_DIR=${DESIGN_DIR}/基础设施层/

mkdir -p $DESIGN_BASE_DIR
mkdir -p $DESIGN_APPLICATION_DIR
mkdir -p $DESIGN_DOMAN_DIR
mkdir -p $DESIGN_VIEW_DIR


