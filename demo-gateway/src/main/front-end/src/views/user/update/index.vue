<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="form.name" clearable placeholder="在这里输入性名" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio label="男" />
          <el-radio label="女" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" type="number" min="0" clearable placeholder="在这里输入年龄" />
      </el-form-item>
      <el-form-item label="生日">
        <el-date-picker v-model="form.birthday" type="date" value-format="yyyy-MM-dd" placeholder="选择出生日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import request from '@/utils/request'
  export default {
    data() {
      return {
        form: {
          userId: '',
          name: '',
          sex: '男',
          age: 18,
          birthday: ''
        }
      }
    },
    methods: {
      onSubmit() {
        var data = {
          userId: this.form.userId,
          name: this.form.name,
          age: this.form.age,
          birthday: this.form.birthday
        }
        const {
          sex
        } = this.form;
        if (sex == '男') {
          data.sex = 0;
        } else if (sex == '女') {
          data.sex = 1;
        }

        request({
          url: "/client/user",
          method: "post",
          data
        }).then(response => {
          var result = response.data;
          if (result.success) {
            this.$router.push({
              path: '/user/manage'
            });
          }
        });

      },
      cancel() {
        this.$router.push({
          path: '/user/manage'
        });
      }
    },
    created() {
      const userId = this.$route.params.userId;
      request({
        url: '/client/user/' + userId,
        method: 'get'
      }).then(response => {
        const result = response.data;
        const { data } = result;
        this.form.name = data.name;
        this.form.age = data.age;
        this.form.birthday = data.birthday;
        if (data.sex == 0) {
          this.form.sex = '男';
        } else if (data.sex == 1) {
          this.form.sex = '女';
        }
        this.form.userId = data.userId;
      });
    }
  }
</script>

<style scoped>

</style>
