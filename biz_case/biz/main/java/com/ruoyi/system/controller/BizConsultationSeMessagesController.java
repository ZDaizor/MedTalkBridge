package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BizConsultationSeMessages;
import com.ruoyi.system.service.IBizConsultationSeMessagesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问诊对话记录详情Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/messages")
public class BizConsultationSeMessagesController extends BaseController
{
    @Autowired
    private IBizConsultationSeMessagesService bizConsultationSeMessagesService;

    /**
     * 查询问诊对话记录详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:messages:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizConsultationSeMessages bizConsultationSeMessages)
    {
        startPage();
        List<BizConsultationSeMessages> list = bizConsultationSeMessagesService.selectBizConsultationSeMessagesList(bizConsultationSeMessages);
        return getDataTable(list);
    }

    /**
     * 导出问诊对话记录详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:messages:export')")
    @Log(title = "问诊对话记录详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizConsultationSeMessages bizConsultationSeMessages)
    {
        List<BizConsultationSeMessages> list = bizConsultationSeMessagesService.selectBizConsultationSeMessagesList(bizConsultationSeMessages);
        ExcelUtil<BizConsultationSeMessages> util = new ExcelUtil<BizConsultationSeMessages>(BizConsultationSeMessages.class);
        util.exportExcel(response, list, "问诊对话记录详情数据");
    }

    /**
     * 获取问诊对话记录详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:messages:query')")
    @GetMapping(value = "/{messagesId}")
    public AjaxResult getInfo(@PathVariable("messagesId") String messagesId)
    {
        return success(bizConsultationSeMessagesService.selectBizConsultationSeMessagesByMessagesId(messagesId));
    }

    /**
     * 新增问诊对话记录详情
     */
    @PreAuthorize("@ss.hasPermi('system:messages:add')")
    @Log(title = "问诊对话记录详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizConsultationSeMessages bizConsultationSeMessages)
    {
        return toAjax(bizConsultationSeMessagesService.insertBizConsultationSeMessages(bizConsultationSeMessages));
    }

    /**
     * 修改问诊对话记录详情
     */
    @PreAuthorize("@ss.hasPermi('system:messages:edit')")
    @Log(title = "问诊对话记录详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizConsultationSeMessages bizConsultationSeMessages)
    {
        return toAjax(bizConsultationSeMessagesService.updateBizConsultationSeMessages(bizConsultationSeMessages));
    }

    /**
     * 删除问诊对话记录详情
     */
    @PreAuthorize("@ss.hasPermi('system:messages:remove')")
    @Log(title = "问诊对话记录详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{messagesIds}")
    public AjaxResult remove(@PathVariable String[] messagesIds)
    {
        return toAjax(bizConsultationSeMessagesService.deleteBizConsultationSeMessagesByMessagesIds(messagesIds));
    }
}
