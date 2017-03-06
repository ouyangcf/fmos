package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2017/1/1.
 */
public class FormModelDto {
    private String formName;
    private String formNumber;
    private String  formContent;

    public FormModelDto() {
    }

    public FormModelDto(String formName, String formNumber, String formContent) {
        this.formName = formName;
        this.formNumber = formNumber;
        this.formContent = formContent;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getFormContent() {
        return formContent;
    }

    public void setFormContent(String formContent) {
        this.formContent = formContent;
    }
}
