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
import com.ruoyi.system.domain.BizCase;
import com.ruoyi.system.service.IBizCaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 病例Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/case")
public class BizCaseController extends BaseController
{
    @Autowired
    private IBizCaseService bizCaseService;

    /**
     * 查询病例列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCase bizCase)
    {
        startPage();
        List<BizCase> list = bizCaseService.selectBizCaseList(bizCase);
        return getDataTable(list);
    }

    /**
     * 导出病例列表
     */
    @PreAuthorize("@ss.hasPermi('system:case:export')")
    @Log(title = "病例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCase bizCase)
    {
        List<BizCase> list = bizCaseService.selectBizCaseList(bizCase);
        ExcelUtil<BizCase> util = new ExcelUtil<BizCase>(BizCase.class);
        util.exportExcel(response, list, "病例数据");
    }

    /**
     * 获取病例详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:case:query')")
    @GetMapping(value = "/{caseId}")
    public AjaxResult getInfo(@PathVariable("caseId") Long caseId)
    {
        return success(bizCaseService.selectBizCaseByCaseId(caseId));
    }

    /**
     * 新增病例
     */
    @PreAuthorize("@ss.hasPermi('system:case:add')")
    @Log(title = "病例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCase bizCase)
    {
        return toAjax(bizCaseService.insertBizCase(bizCase));
    }

    /**
     * 修改病例
     */
    @PreAuthorize("@ss.hasPermi('system:case:edit')")
    @Log(title = "病例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCase bizCase)
    {
        return toAjax(bizCaseService.updateBizCase(bizCase));
    }

    /**
     * 删除病例
     */
    @PreAuthorize("@ss.hasPermi('system:case:remove')")
    @Log(title = "病例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{caseIds}")
    public AjaxResult remove(@PathVariable Long[] caseIds)
    {
        return toAjax(bizCaseService.deleteBizCaseByCaseIds(caseIds));
    }
}
