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
import com.ruoyi.system.domain.BizEvScoreDetails;
import com.ruoyi.system.service.IBizEvScoreDetailsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内容得分详情Controller
 * 
 * @author daizor
 * @date 2025-09-03
 */
@RestController
@RequestMapping("/system/details")
public class BizEvScoreDetailsController extends BaseController
{
    @Autowired
    private IBizEvScoreDetailsService bizEvScoreDetailsService;

    /**
     * 查询内容得分详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizEvScoreDetails bizEvScoreDetails)
    {
        startPage();
        List<BizEvScoreDetails> list = bizEvScoreDetailsService.selectBizEvScoreDetailsList(bizEvScoreDetails);
        return getDataTable(list);
    }

    /**
     * 导出内容得分详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:details:export')")
    @Log(title = "内容得分详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizEvScoreDetails bizEvScoreDetails)
    {
        List<BizEvScoreDetails> list = bizEvScoreDetailsService.selectBizEvScoreDetailsList(bizEvScoreDetails);
        ExcelUtil<BizEvScoreDetails> util = new ExcelUtil<BizEvScoreDetails>(BizEvScoreDetails.class);
        util.exportExcel(response, list, "内容得分详情数据");
    }

    /**
     * 获取内容得分详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:details:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return success(bizEvScoreDetailsService.selectBizEvScoreDetailsByScoreId(scoreId));
    }

    /**
     * 新增内容得分详情
     */
    @PreAuthorize("@ss.hasPermi('system:details:add')")
    @Log(title = "内容得分详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizEvScoreDetails bizEvScoreDetails)
    {
        return toAjax(bizEvScoreDetailsService.insertBizEvScoreDetails(bizEvScoreDetails));
    }

    /**
     * 修改内容得分详情
     */
    @PreAuthorize("@ss.hasPermi('system:details:edit')")
    @Log(title = "内容得分详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizEvScoreDetails bizEvScoreDetails)
    {
        return toAjax(bizEvScoreDetailsService.updateBizEvScoreDetails(bizEvScoreDetails));
    }

    /**
     * 删除内容得分详情
     */
    @PreAuthorize("@ss.hasPermi('system:details:remove')")
    @Log(title = "内容得分详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(bizEvScoreDetailsService.deleteBizEvScoreDetailsByScoreIds(scoreIds));
    }
}
