<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="${comment}" prop="caseId">
        <el-input
          v-model="queryParams.caseId"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="步骤 id" prop="stepId">
        <el-input
          v-model="queryParams.stepId"
          placeholder="请输入步骤 id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分项名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入分项名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="默认满分" prop="defaultMaxScore">
        <el-input
          v-model="queryParams.defaultMaxScore"
          placeholder="请输入默认满分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示顺序，用于前端排序" prop="displayOrder">
        <el-input
          v-model="queryParams.displayOrder"
          placeholder="请输入显示顺序，用于前端排序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否可用" prop="isActive">
        <el-input
          v-model="queryParams.isActive"
          placeholder="请输入是否可用"
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
          v-hasPermi="['system:templates:add']"
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
          v-hasPermi="['system:templates:edit']"
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
          v-hasPermi="['system:templates:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:templates:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="templatesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="templatesId" />
      <el-table-column label="${comment}" align="center" prop="caseId" />
      <el-table-column label="步骤 id" align="center" prop="stepId" />
      <el-table-column label="分项名称" align="center" prop="itemName" />
      <el-table-column label="默认满分" align="center" prop="defaultMaxScore" />
      <el-table-column label="显示顺序，用于前端排序" align="center" prop="displayOrder" />
      <el-table-column label="是否可用" align="center" prop="isActive" />
      <el-table-column label="模板类型 " align="center" prop="templateType" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:templates:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:templates:remove']"
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

    <!-- 添加或修改内容得分模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="${comment}" prop="caseId">
          <el-input v-model="form.caseId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="步骤 id" prop="stepId">
          <el-input v-model="form.stepId" placeholder="请输入步骤 id" />
        </el-form-item>
        <el-form-item label="分项名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入分项名称" />
        </el-form-item>
        <el-form-item label="默认满分" prop="defaultMaxScore">
          <el-input v-model="form.defaultMaxScore" placeholder="请输入默认满分" />
        </el-form-item>
        <el-form-item label="显示顺序，用于前端排序" prop="displayOrder">
          <el-input v-model="form.displayOrder" placeholder="请输入显示顺序，用于前端排序" />
        </el-form-item>
        <el-form-item label="是否可用" prop="isActive">
          <el-input v-model="form.isActive" placeholder="请输入是否可用" />
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
import { listTemplates, getTemplates, delTemplates, addTemplates, updateTemplates } from "@/api/system/templates"

export default {
  name: "Templates",
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
      // 内容得分模板表格数据
      templatesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseId: null,
        stepId: null,
        itemName: null,
        defaultMaxScore: null,
        displayOrder: null,
        isActive: null,
        templateType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询内容得分模板列表 */
    getList() {
      this.loading = true
      listTemplates(this.queryParams).then(response => {
        this.templatesList = response.rows
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
        templatesId: null,
        caseId: null,
        stepId: null,
        itemName: null,
        defaultMaxScore: null,
        displayOrder: null,
        isActive: null,
        templateType: null
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
      this.ids = selection.map(item => item.templatesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加内容得分模板"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const templatesId = row.templatesId || this.ids
      getTemplates(templatesId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改内容得分模板"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.templatesId != null) {
            updateTemplates(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTemplates(this.form).then(response => {
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
      const templatesIds = row.templatesId || this.ids
      this.$modal.confirm('是否确认删除内容得分模板编号为"' + templatesIds + '"的数据项？').then(function() {
        return delTemplates(templatesIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/templates/export', {
        ...this.queryParams
      }, `templates_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
