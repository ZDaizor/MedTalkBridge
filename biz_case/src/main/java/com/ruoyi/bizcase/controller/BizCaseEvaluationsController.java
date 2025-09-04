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
import com.ruoyi.bizcase.domain.BizCaseEvaluations;
import com.ruoyi.bizcase.service.IBizCaseEvaluationsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生病例得分总Controller
 * 
 * @author daizor
 * @date 2025-09-04
 */
@RestController
@RequestMapping("/system/evaluations")
public class BizCaseEvaluationsController extends BaseController
{
    @Autowired
    private IBizCaseEvaluationsService bizCaseEvaluationsService;

    /**
     * 查询学生病例得分总列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizCaseEvaluations bizCaseEvaluations)
    {
        startPage();
        List<BizCaseEvaluations> list = bizCaseEvaluationsService.selectBizCaseEvaluationsList(bizCaseEvaluations);
        return getDataTable(list);
    }

    /**
     * 导出学生病例得分总列表
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:export')")
    @Log(title = "学生病例得分总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizCaseEvaluations bizCaseEvaluations)
    {
        List<BizCaseEvaluations> list = bizCaseEvaluationsService.selectBizCaseEvaluationsList(bizCaseEvaluations);
        ExcelUtil<BizCaseEvaluations> util = new ExcelUtil<BizCaseEvaluations>(BizCaseEvaluations.class);
        util.exportExcel(response, list, "学生病例得分总数据");
    }

    /**
     * 获取学生病例得分总详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:query')")
    @GetMapping(value = "/{evaluationId}")
    public AjaxResult getInfo(@PathVariable("evaluationId") Long evaluationId)
    {
        return success(bizCaseEvaluationsService.selectBizCaseEvaluationsByEvaluationId(evaluationId));
    }

    /**
     * 新增学生病例得分总
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:add')")
    @Log(title = "学生病例得分总", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizCaseEvaluations bizCaseEvaluations)
    {
        return toAjax(bizCaseEvaluationsService.insertBizCaseEvaluations(bizCaseEvaluations));
    }

    /**
     * 修改学生病例得分总
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:edit')")
    @Log(title = "学生病例得分总", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizCaseEvaluations bizCaseEvaluations)
    {
        return toAjax(bizCaseEvaluationsService.updateBizCaseEvaluations(bizCaseEvaluations));
    }

    /**
     * 删除学生病例得分总
     */
    @PreAuthorize("@ss.hasPermi('system:evaluations:remove')")
    @Log(title = "学生病例得分总", businessType = BusinessType.DELETE)
	@DeleteMapping("/{evaluationIds}")
    public AjaxResult remove(@PathVariable Long[] evaluationIds)
    {
        return toAjax(bizCaseEvaluationsService.deleteBizCaseEvaluationsByEvaluationIds(evaluationIds));
    }
}
