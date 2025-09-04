<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="病例名称" prop="caseName">
        <el-input
          v-model="queryParams.caseName"
          placeholder="请输入病例名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="病例编码" prop="caseCode">
        <el-input
          v-model="queryParams.caseCode"
          placeholder="请输入病例编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者年龄" prop="patientAge">
        <el-input
          v-model="queryParams.patientAge"
          placeholder="请输入患者年龄"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者性别" prop="patientGender">
        <el-input
          v-model="queryParams.patientGender"
          placeholder="请输入患者性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摘要" prop="caseAbstract">
        <el-input
          v-model="queryParams.caseAbstract"
          placeholder="请输入摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="现病史" prop="caseHpi">
        <el-input
          v-model="queryParams.caseHpi"
          placeholder="请输入现病史"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过敏史" prop="caseHa">
        <el-input
          v-model="queryParams.caseHa"
          placeholder="请输入过敏史"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="既往史" prop="casePh">
        <el-input
          v-model="queryParams.casePh"
          placeholder="请输入既往史"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主诉" prop="caseAic">
        <el-input
          v-model="queryParams.caseAic"
          placeholder="请输入主诉"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="查体" prop="caseHe">
        <el-input
          v-model="queryParams.caseHe"
          placeholder="请输入查体"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="辅助检查" prop="caseAe">
        <el-input
          v-model="queryParams.caseAe"
          placeholder="请输入辅助检查"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="家族史" prop="caseFh">
        <el-input
          v-model="queryParams.caseFh"
          placeholder="请输入家族史"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="传染病学史" prop="caseHid">
        <el-input
          v-model="queryParams.caseHid"
          placeholder="请输入传染病学史"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="诊断参考" prop="caseDr">
        <el-input
          v-model="queryParams.caseDr"
          placeholder="请输入诊断参考"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问诊最大时间" prop="caseMax">
        <el-input
          v-model="queryParams.caseMax"
          placeholder="请输入问诊最大时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:case:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:case:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:case:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:case:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="caseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="caseId" />
      <el-table-column label="病例名称" align="center" prop="caseName" />
      <el-table-column label="病例编码" align="center" prop="caseCode" />
      <el-table-column label="病例状态" align="center" prop="caseStatus" />
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <el-table-column label="患者年龄" align="center" prop="patientAge" />
      <el-table-column label="患者性别" align="center" prop="patientGender" />
      <el-table-column label="症状分类" align="center" prop="caseSectionsType" />
      <el-table-column label="摘要" align="center" prop="caseAbstract" />
      <el-table-column label="现病史" align="center" prop="caseHpi" />
      <el-table-column label="过敏史" align="center" prop="caseHa" />
      <el-table-column label="既往史" align="center" prop="casePh" />
      <el-table-column label="主诉" align="center" prop="caseAic" />
      <el-table-column label="查体" align="center" prop="caseHe" />
      <el-table-column label="辅助检查" align="center" prop="caseAe" />
      <el-table-column label="家族史" align="center" prop="caseFh" />
      <el-table-column label="传染病学史" align="center" prop="caseHid" />
      <el-table-column label="诊断参考" align="center" prop="caseDr" />
      <el-table-column label="内容提示词" align="center" prop="contentWords" />
      <el-table-column label="整体提示词" align="center" prop="totleWords" />
      <el-table-column label="病志书写评分提示词" align="center" prop="writeWords" />
      <el-table-column label="问诊最大时间" align="center" prop="caseMax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:case:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:case:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改病例对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="病例名称" prop="caseName">
          <el-input v-model="form.caseName" placeholder="请输入病例名称" />
        </el-form-item>
        <el-form-item label="病例编码" prop="caseCode">
          <el-input v-model="form.caseCode" placeholder="请输入病例编码" />
        </el-form-item>
        <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" placeholder="请输入患者姓名" />
        </el-form-item>
        <el-form-item label="患者年龄" prop="patientAge">
          <el-input v-model="form.patientAge" placeholder="请输入患者年龄" />
        </el-form-item>
        <el-form-item label="患者性别" prop="patientGender">
          <el-input v-model="form.patientGender" placeholder="请输入患者性别" />
        </el-form-item>
        <el-form-item label="摘要" prop="caseAbstract">
          <el-input v-model="form.caseAbstract" placeholder="请输入摘要" />
        </el-form-item>
        <el-form-item label="现病史" prop="caseHpi">
          <el-input v-model="form.caseHpi" placeholder="请输入现病史" />
        </el-form-item>
        <el-form-item label="过敏史" prop="caseHa">
          <el-input v-model="form.caseHa" placeholder="请输入过敏史" />
        </el-form-item>
        <el-form-item label="既往史" prop="casePh">
          <el-input v-model="form.casePh" placeholder="请输入既往史" />
        </el-form-item>
        <el-form-item label="主诉" prop="caseAic">
          <el-input v-model="form.caseAic" placeholder="请输入主诉" />
        </el-form-item>
        <el-form-item label="查体" prop="caseHe">
          <el-input v-model="form.caseHe" placeholder="请输入查体" />
        </el-form-item>
        <el-form-item label="辅助检查" prop="caseAe">
          <el-input v-model="form.caseAe" placeholder="请输入辅助检查" />
        </el-form-item>
        <el-form-item label="家族史" prop="caseFh">
          <el-input v-model="form.caseFh" placeholder="请输入家族史" />
        </el-form-item>
        <el-form-item label="传染病学史" prop="caseHid">
          <el-input v-model="form.caseHid" placeholder="请输入传染病学史" />
        </el-form-item>
        <el-form-item label="诊断参考" prop="caseDr">
          <el-input v-model="form.caseDr" placeholder="请输入诊断参考" />
        </el-form-item>
        <el-form-item label="内容提示词" prop="contentWords">
          <el-input v-model="form.contentWords" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整体提示词" prop="totleWords">
          <el-input v-model="form.totleWords" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="病志书写评分提示词" prop="writeWords">
          <el-input v-model="form.writeWords" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="问诊最大时间" prop="caseMax">
          <el-input v-model="form.caseMax" placeholder="请输入问诊最大时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCase, getCase, delCase, addCase, updateCase } from "@/api/system/case"

export default {
  name: "Case",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 病例表格数据
      caseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseName: null,
        caseCode: null,
        caseStatus: null,
        patientName: null,
        patientAge: null,
        patientGender: null,
        caseSectionsType: null,
        caseAbstract: null,
        caseHpi: null,
        caseHa: null,
        casePh: null,
        caseAic: null,
        caseHe: null,
        caseAe: null,
        caseFh: null,
        caseHid: null,
        caseDr: null,
        contentWords: null,
        totleWords: null,
        writeWords: null,
        caseMax: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        caseName: [
          { required: true, message: "病例名称不能为空", trigger: "blur" }
        ],
        caseCode: [
          { required: true, message: "病例编码不能为空", trigger: "blur" }
        ],
        caseStatus: [
          { required: true, message: "病例状态不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询病例列表 */
    getList() {
      this.loading = true
      listCase(this.queryParams).then(response => {
        this.caseList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        caseId: null,
        caseName: null,
        caseCode: null,
        caseStatus: null,
        patientName: null,
        patientAge: null,
        patientGender: null,
        caseSectionsType: null,
        caseAbstract: null,
        caseHpi: null,
        caseHa: null,
        casePh: null,
        caseAic: null,
        caseHe: null,
        caseAe: null,
        caseFh: null,
        caseHid: null,
        caseDr: null,
        contentWords: null,
        totleWords: null,
        writeWords: null,
        caseMax: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.caseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加病例"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const caseId = row.caseId || this.ids
      getCase(caseId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改病例"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.caseId != null) {
            updateCase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addCase(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const caseIds = row.caseId || this.ids
      this.$modal.confirm('是否确认删除病例编号为"' + caseIds + '"的数据项？').then(function() {
        return delCase(caseIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/case/export', {
        ...this.queryParams
      }, `case_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
