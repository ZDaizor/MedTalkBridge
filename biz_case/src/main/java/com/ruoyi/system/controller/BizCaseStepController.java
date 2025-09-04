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
import com.ruoyi.system.domain.BizCaseStep;
import com.ruoyi.system.service.IBizCaseStepService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author daizor
 * @date 2025-09-04
 */
@RestController
@RequestMapping("/system/step")
public class BizCaseStepController extends BaseController
{
    @Autowired
    private IBizCaseStepService bizCaseStepService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:step:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCaseStep bizCaseStep)
    {
        startPage();
        List<BizCaseStep> list = bizCaseStepService.selectBizCaseStepList(bizCaseStep);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:step:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCaseStep bizCaseStep)
    {
        List<BizCaseStep> list = bizCaseStepService.selectBizCaseStepList(bizCaseStep);
        ExcelUtil<BizCaseStep> util = new ExcelUtil<BizCaseStep>(BizCaseStep.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:step:query')")
    @GetMapping(value = "/{stepId}")
    public AjaxResult getInfo(@PathVariable("stepId") Long stepId)
    {
        return success(bizCaseStepService.selectBizCaseStepByStepId(stepId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCaseStep bizCaseStep)
    {
        return toAjax(bizCaseStepService.insertBizCaseStep(bizCaseStep));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCaseStep bizCaseStep)
    {
        return toAjax(bizCaseStepService.updateBizCaseStep(bizCaseStep));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:step:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stepIds}")
    public AjaxResult remove(@PathVariable Long[] stepIds)
    {
        return toAjax(bizCaseStepService.deleteBizCaseStepByStepIds(stepIds));
    }
}
