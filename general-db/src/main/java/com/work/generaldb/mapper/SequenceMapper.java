package com.work.generaldb.mapper;


import org.apache.ibatis.annotations.Select;

public interface SequenceMapper {

    @Select("SELECT NEXTVAL(#{seqName}) ")
    String getSeqNextVal(String seqName);

}
