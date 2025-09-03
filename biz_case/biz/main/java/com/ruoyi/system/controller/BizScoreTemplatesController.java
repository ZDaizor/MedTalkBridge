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
import com.ruoyi.system.domain.BizScoreTemplates;
import com.ruoyi.system.service.IBizScoreTemplatesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内容得分模板Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/templates")
public class BizScoreTemplatesController extends BaseController
{
    @Autowired
    private IBizScoreTemplatesService bizScoreTemplatesService;

    /**
     * 查询内容得分模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:templates:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizScoreTemplates bizScoreTemplates)
    {
        startPage();
        List<BizScoreTemplates> list = bizScoreTemplatesService.selectBizScoreTemplatesList(bizScoreTemplates);
        return getDataTable(list);
    }

    /**
     * 导出内容得分模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:templates:export')")
    @Log(title = "内容得分模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizScoreTemplates bizScoreTemplates)
    {
        List<BizScoreTemplates> list = bizScoreTemplatesService.selectBizScoreTemplatesList(bizScoreTemplates);
        ExcelUtil<BizScoreTemplates> util = new ExcelUtil<BizScoreTemplates>(BizScoreTemplates.class);
        util.exportExcel(response, list, "内容得分模板数据");
    }

    /**
     * 获取内容得分模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:templates:query')")
    @GetMapping(value = "/{templatesId}")
    public AjaxResult getInfo(@PathVariable("templatesId") Long templatesId)
    {
        return success(bizScoreTemplatesService.selectBizScoreTemplatesByTemplatesId(templatesId));
    }

    /**
     * 新增内容得分模板
     */
    @PreAuthorize("@ss.hasPermi('system:templates:add')")
    @Log(title = "内容得分模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizScoreTemplates bizScoreTemplates)
    {
        return toAjax(bizScoreTemplatesService.insertBizScoreTemplates(bizScoreTemplates));
    }

    /**
     * 修改内容得分模板
     */
    @PreAuthorize("@ss.hasPermi('system:templates:edit')")
    @Log(title = "内容得分模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizScoreTemplates bizScoreTemplates)
    {
        return toAjax(bizScoreTemplatesService.updateBizScoreTemplates(bizScoreTemplates));
    }

    /**
     * 删除内容得分模板
     */
    @PreAuthorize("@ss.hasPermi('system:templates:remove')")
    @Log(title = "内容得分模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{templatesIds}")
    public AjaxResult remove(@PathVariable Long[] templatesIds)
    {
        return toAjax(bizScoreTemplatesService.deleteBizScoreTemplatesByTemplatesIds(templatesIds));
    }
}
