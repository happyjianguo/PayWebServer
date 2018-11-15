package com.work.generaldb.mapper;


import org.apache.ibatis.annotations.Select;

public interface SequenceMapper {

//    @Select("SELECT NEXTVAL('#{seqName}'); ")
    @Select("SELECT NEXTVAL('OrderSeq'); ")
    String getSeqNextVal(String seqName);

}
