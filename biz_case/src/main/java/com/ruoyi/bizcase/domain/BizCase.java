package com.ruoyi.bizcase.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 病例对象 biz_case
 * 
 * @author daizor
 * @date 2025-09-04
 */
public class BizCase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long caseId;

    /** 病例名称 */
    @Excel(name = "病例名称")
    private String caseName;

    /** 病例编码 */
    @Excel(name = "病例编码")
    private String caseCode;

    /** 病例状态（0：停用，1：可用） */
    @Excel(name = "病例状态", readConverterExp = "0=：停用，1：可用")
    private Long caseStatus;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者年龄 */
    @Excel(name = "患者年龄")
    private Long patientAge;

    /** 患者性别（1：男，2 女） */
    @Excel(name = "患者性别", readConverterExp = "1=：男，2,女=")
    private Long patientGender;

    /** 症状分类 */
    @Excel(name = "症状分类")
    private Long caseSectionsType;

    /** 摘要 */
    @Excel(name = "摘要")
    private String caseAbstract;

    /** 现病史 */
    @Excel(name = "现病史")
    private String caseHpi;

    /** 过敏史 */
    @Excel(name = "过敏史")
    private String caseHa;

    /** 既往史 */
    @Excel(name = "既往史")
    private String casePh;

    /** 主诉 */
    @Excel(name = "主诉")
    private String caseAic;

    /** 查体 */
    @Excel(name = "查体")
    private String caseHe;

    /** 辅助检查 */
    @Excel(name = "辅助检查")
    private String caseAe;

    /** 家族史 */
    @Excel(name = "家族史")
    private String caseFh;

    /** 传染病学史 */
    @Excel(name = "传染病学史")
    private String caseHid;

    /** 诊断参考 */
    @Excel(name = "诊断参考")
    private String caseDr;

    /** 内容提示词 */
    @Excel(name = "内容提示词")
    private String contentWords;

    /** 整体提示词 */
    @Excel(name = "整体提示词")
    private String totleWords;

    /** 病志书写评分提示词 */
    @Excel(name = "病志书写评分提示词")
    private String writeWords;

    /** 问诊最大时间 */
    @Excel(name = "问诊最大时间")
    private Long caseMax;

    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
    }

    public void setCaseName(String caseName) 
    {
        this.caseName = caseName;
    }

    public String getCaseName() 
    {
        return caseName;
    }

    public void setCaseCode(String caseCode) 
    {
        this.caseCode = caseCode;
    }

    public String getCaseCode() 
    {
        return caseCode;
    }

    public void setCaseStatus(Long caseStatus) 
    {
        this.caseStatus = caseStatus;
    }

    public Long getCaseStatus() 
    {
        return caseStatus;
    }

    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }

    public void setPatientAge(Long patientAge) 
    {
        this.patientAge = patientAge;
    }

    public Long getPatientAge() 
    {
        return patientAge;
    }

    public void setPatientGender(Long patientGender) 
    {
        this.patientGender = patientGender;
    }

    public Long getPatientGender() 
    {
        return patientGender;
    }

    public void setCaseSectionsType(Long caseSectionsType) 
    {
        this.caseSectionsType = caseSectionsType;
    }

    public Long getCaseSectionsType() 
    {
        return caseSectionsType;
    }

    public void setCaseAbstract(String caseAbstract) 
    {
        this.caseAbstract = caseAbstract;
    }

    public String getCaseAbstract() 
    {
        return caseAbstract;
    }

    public void setCaseHpi(String caseHpi) 
    {
        this.caseHpi = caseHpi;
    }

    public String getCaseHpi() 
    {
        return caseHpi;
    }

    public void setCaseHa(String caseHa) 
    {
        this.caseHa = caseHa;
    }

    public String getCaseHa() 
    {
        return caseHa;
    }

    public void setCasePh(String casePh) 
    {
        this.casePh = casePh;
    }

    public String getCasePh() 
    {
        return casePh;
    }

    public void setCaseAic(String caseAic) 
    {
        this.caseAic = caseAic;
    }

    public String getCaseAic() 
    {
        return caseAic;
    }

    public void setCaseHe(String caseHe) 
    {
        this.caseHe = caseHe;
    }

    public String getCaseHe() 
    {
        return caseHe;
    }

    public void setCaseAe(String caseAe) 
    {
        this.caseAe = caseAe;
    }

    public String getCaseAe() 
    {
        return caseAe;
    }

    public void setCaseFh(String caseFh) 
    {
        this.caseFh = caseFh;
    }

    public String getCaseFh() 
    {
        return caseFh;
    }

    public void setCaseHid(String caseHid) 
    {
        this.caseHid = caseHid;
    }

    public String getCaseHid() 
    {
        return caseHid;
    }

    public void setCaseDr(String caseDr) 
    {
        this.caseDr = caseDr;
    }

    public String getCaseDr() 
    {
        return caseDr;
    }

    public void setContentWords(String contentWords) 
    {
        this.contentWords = contentWords;
    }

    public String getContentWords() 
    {
        return contentWords;
    }

    public void setTotleWords(String totleWords) 
    {
        this.totleWords = totleWords;
    }

    public String getTotleWords() 
    {
        return totleWords;
    }

    public void setWriteWords(String writeWords) 
    {
        this.writeWords = writeWords;
    }

    public String getWriteWords() 
    {
        return writeWords;
    }

    public void setCaseMax(Long caseMax) 
    {
        this.caseMax = caseMax;
    }

    public Long getCaseMax() 
    {
        return caseMax;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("caseId", getCaseId())
            .append("caseName", getCaseName())
            .append("caseCode", getCaseCode())
            .append("caseStatus", getCaseStatus())
            .append("patientName", getPatientName())
            .append("patientAge", getPatientAge())
            .append("patientGender", getPatientGender())
            .append("caseSectionsType", getCaseSectionsType())
            .append("caseAbstract", getCaseAbstract())
            .append("caseHpi", getCaseHpi())
            .append("caseHa", getCaseHa())
            .append("casePh", getCasePh())
            .append("caseAic", getCaseAic())
            .append("caseHe", getCaseHe())
            .append("caseAe", getCaseAe())
            .append("caseFh", getCaseFh())
            .append("caseHid", getCaseHid())
            .append("caseDr", getCaseDr())
            .append("contentWords", getContentWords())
            .append("totleWords", getTotleWords())
            .append("writeWords", getWriteWords())
            .append("caseMax", getCaseMax())
            .toString();
    }
}
