package com.pwpew.entity;

import java.util.Objects;

/**
 * @author miaoyin
 * @date 2020/5/21 - 17:28
 * @commet:
 */
public class TDatadict {
    private int dictId;
    private String dictTypeCode;
    private String dictTypeName;
    private String dictItemName;
    private Integer dictSort;
    private String dictEnable;
    private String dictComment;

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictEnable() {
        return dictEnable;
    }

    public void setDictEnable(String dictEnable) {
        this.dictEnable = dictEnable;
    }

    public String getDictComment() {
        return dictComment;
    }

    public void setDictComment(String dictComment) {
        this.dictComment = dictComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TDatadict tDatadict = (TDatadict) o;
        return dictId == tDatadict.dictId &&
                Objects.equals(dictTypeCode, tDatadict.dictTypeCode) &&
                Objects.equals(dictTypeName, tDatadict.dictTypeName) &&
                Objects.equals(dictItemName, tDatadict.dictItemName) &&
                Objects.equals(dictSort, tDatadict.dictSort) &&
                Objects.equals(dictEnable, tDatadict.dictEnable) &&
                Objects.equals(dictComment, tDatadict.dictComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dictId, dictTypeCode, dictTypeName, dictItemName, dictSort, dictEnable, dictComment);
    }
}
