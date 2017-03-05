package com.clouddemo.vo;


/**
 * Created by zzf on 2017/2/14.
 */
public class SysUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String DT_RowId;
    private RowData rowData;

    public RowData getRowData() {
        return rowData;
    }

    public void setRowData(RowData rowData) {
        this.rowData = rowData;
    }

    public String getDT_RowId() {
        return DT_RowId;
    }

    public void setDT_RowId(String DT_RowId) {
        this.DT_RowId = DT_RowId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
