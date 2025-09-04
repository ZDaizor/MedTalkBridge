package com.ruoyi.bizcase.controller;

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
import com.ruoyi.bizcase.domain.BizCasePrompt;
import com.ruoyi.bizcase.service.IBizCasePromptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author daizor
 * @date 2025-09-04
 */
@RestController
@RequestMapping("/system/prompt")
public class BizCasePromptController extends BaseController
{
    @Autowired
    private IBizCasePromptService bizCasePromptService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCasePrompt bizCasePrompt)
    {
        startPage();
        List<BizCasePrompt> list = bizCasePromptService.selectBizCasePromptList(bizCasePrompt);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCasePrompt bizCasePrompt)
    {
        List<BizCasePrompt> list = bizCasePromptService.selectBizCasePromptList(bizCasePrompt);
        ExcelUtil<BizCasePrompt> util = new ExcelUtil<BizCasePrompt>(BizCasePrompt.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:query')")
    @GetMapping(value = "/{promptId}")
    public AjaxResult getInfo(@PathVariable("promptId") Long promptId)
    {
        return success(bizCasePromptService.selectBizCasePromptByPromptId(promptId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCasePrompt bizCasePrompt)
    {
        return toAjax(bizCasePromptService.insertBizCasePrompt(bizCasePrompt));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCasePrompt bizCasePrompt)
    {
        return toAjax(bizCasePromptService.updateBizCasePrompt(bizCasePrompt));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:prompt:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{promptIds}")
    public AjaxResult remove(@PathVariable Long[] promptIds)
    {
        return toAjax(bizCasePromptService.deleteBizCasePromptByPromptIds(promptIds));
    }
}
