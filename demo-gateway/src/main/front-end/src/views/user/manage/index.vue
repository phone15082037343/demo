<template>
  <div class="app-container">
    <div class="btns">
      <el-tooltip class="item" effect="dark" content="添加" placement="top">
        <el-button type="primary" size="medium" icon="el-icon-plus" @click="toAddPage" />
      </el-tooltip>

    </div>
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column type="selection" width="55" prop="userId" align="center" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="sex" label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.sex == 0">男</span>
          <span v-else-if="scope.row.sex == 1">女</span>
        </template>
      </el-table-column>
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="birthday" label="生日" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.currentPage"
        :page-sizes="[10, 20, 50, 100]" :page-size="page.size" layout="total, sizes, prev, pager, next, jumper" :total="page.total" />
    </div>
  </div>
</template>

<script>
  import request from '@/utils/request'
  export default {
    data() {
      return {
        tableData: [],
        page: {
          currentPage: 1,
          size: 10,
          total: 101
        }
      }
    },
    methods: {
      handleSizeChange(val) {
        this.page.size = val;
        this.page.currentPage = 1;
        this.loadTableData();
      },
      handleCurrentChange(val) {
        this.page.currentPage = val;
        this.loadTableData();
      },
      loadTableData() {
        request({
          url: "/client/user",
          method: "get",
          params: {
            pageNo: this.page.currentPage,
            size: this.page.size
          }
        }).then(response => {
          const result = response.data;
          const {
            data
          } = result;
          this.tableData = data.list;
          this.page.total = data.totalCount;
        });
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      }
    },
    created() {
      this.loadTableData();
    },
    toAddPage() {
      
    }
  }
</script>
<style scoped>
  .block {
    text-align: right;
  }
  .btns {
    margin-bottom: 10px;
  }
</style>
