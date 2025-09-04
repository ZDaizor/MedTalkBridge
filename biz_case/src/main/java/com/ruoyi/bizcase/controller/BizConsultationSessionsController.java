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
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生问诊列Controller
 * 
 * @author daizor
 * @date 2025-09-04
 */
@RestController
@RequestMapping("/system/sessions")
public class BizConsultationSessionsController extends BaseController
{
    @Autowired
    private IBizConsultationSessionsService bizConsultationSessionsService;

    /**
     * 查询学生问诊列列表
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizConsultationSessions bizConsultationSessions)
    {
        startPage();
        List<BizConsultationSessions> list = bizConsultationSessionsService.selectBizConsultationSessionsList(bizConsultationSessions);
        return getDataTable(list);
    }

    /**
     * 导出学生问诊列列表
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:export')")
    @Log(title = "学生问诊列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizConsultationSessions bizConsultationSessions)
    {
        List<BizConsultationSessions> list = bizConsultationSessionsService.selectBizConsultationSessionsList(bizConsultationSessions);
        ExcelUtil<BizConsultationSessions> util = new ExcelUtil<BizConsultationSessions>(BizConsultationSessions.class);
        util.exportExcel(response, list, "学生问诊列数据");
    }

    /**
     * 获取学生问诊列详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:query')")
    @GetMapping(value = "/{sessionId}")
    public AjaxResult getInfo(@PathVariable("sessionId") String sessionId)
    {
        return success(bizConsultationSessionsService.selectBizConsultationSessionsBySessionId(sessionId));
    }

    /**
     * 新增学生问诊列
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:add')")
    @Log(title = "学生问诊列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizConsultationSessions bizConsultationSessions)
    {
        return toAjax(bizConsultationSessionsService.insertBizConsultationSessions(bizConsultationSessions));
    }

    /**
     * 修改学生问诊列
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:edit')")
    @Log(title = "学生问诊列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizConsultationSessions bizConsultationSessions)
    {
        return toAjax(bizConsultationSessionsService.updateBizConsultationSessions(bizConsultationSessions));
    }

    /**
     * 删除学生问诊列
     */
    @PreAuthorize("@ss.hasPermi('system:sessions:remove')")
    @Log(title = "学生问诊列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sessionIds}")
    public AjaxResult remove(@PathVariable String[] sessionIds)
    {
        return toAjax(bizConsultationSessionsService.deleteBizConsultationSessionsBySessionIds(sessionIds));
    }
}
