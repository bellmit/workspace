<template>
    <div>
        <!--面包屑-->
        <el-breadcrumb separator="/">
          <span class="el-breadcrumb__item"><span>我的位置：</span></span>
            <el-breadcrumb-item :to="{ path: '/' }">聚贸BI</el-breadcrumb-item>
            <el-breadcrumb-item>个人信息</el-breadcrumb-item>
        </el-breadcrumb>

        <el-row class="jm-grid-box-bg-white jm-grid-border-1px jm-margin-top20">
            <header class="panel-header jm-grid-border-bottom-1px c-header"><span>基本信息</span></header>
            <div class="clearfix userMsg-box">
                <div class="left">
                    <img src="../assets/images/company/userMsg.png" alt="">
                    <p>认证会员</p>
                </div>
                <dl class="userMsg-list">
                    <dt>用户名：</dt>
                    <dd>zhaoyuhao</dd>
                    <dt>姓名：</dt>
                    <dd>赵玉浩</dd>
                    <dt>角色名称：</dt>
                    <dd>产品经理</dd>
                    <dt>所属部门：</dt>
                    <dd>技术部</dd>
                    <dt>密码：</dt>
                    <dd>
                        <span class="userMsg-password">************</span>
                        <button type="button" class="changePassword-btn" @click="dialogFormVisible = true">修改密码</button>
                    </dd>
                </dl>
            </div>
        </el-row>
        <el-dialog title="密码修改" class="userMsg-password-box" v-model="dialogFormVisible">
                <el-form :model="form">
                    <el-form-item label="原始密码" :label-width="formLabelWidth">
                        <el-input v-model="form.oldPassword" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="输入新密码" :label-width="formLabelWidth">
                        <el-input v-model="form.newPassword" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" :label-width="formLabelWidth">
                        <el-input v-model="form.confimNewPassword" auto-complete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="dialogFormVisible = false">提交</el-button>
                </div>
            </el-dialog>
    </div>
</template>
<script>
    export default {
        name: 'userMsg',
        components: {},
        data() {
            return {
                dialogTableVisible: false,
                dialogFormVisible: false,
                form: {
                    oldPassword: '',
                    newPassword: '',
                    confimNewPassword: ''
                },
                formLabelWidth: '120px'
            }
        }
    }
</script>
<style>
    .userMsg-box{
        padding: 40px 0 0 0;
        font-size: 14px;
        color: #666666;
        min-height: 600px;
    }
    .userMsg-box .left{
        width: 240px;
        text-align: center;
    }
    .userMsg-box .left p{
        margin-top: 20px;
    }
    .userMsg-list{
        min-width: 600px;
        float: left;
        margin: 0;
    }
    .userMsg-list dt{
        float: left;
        width: 100px;
        text-align: right;
        margin-right: 20px;
        height: 30px;
        line-height: 30px;
    }
    .userMsg-list dd{
        padding-bottom: 20px;
        line-height: 30px;
        height: 30px;
    }
    .userMsg-password{
        vertical-align: middle;
    }
    .changePassword-btn{
        width: 100px;
        height: 30px;
        margin-left: 100px;
        text-align: center;
        border-radius: 2px;
        color: #0086e9;
        border: 1px solid #0086e9;
        background-color: white;
        cursor: pointer;
    }
    .changePassword-btn:hover{
        background-color: #0086e9;
        color: #ffffff;
    }
    .userMsg-password-box .el-dialog--small{
        width: 430px;
        height: 310px;
    }
    .userMsg-password-box .el-input__inner{
        width: 220px;
        height: 30px;
        border: 1px solid #cbcbcb;
    }
    .userMsg-password-box .el-input__inner:focus{
        border: 1px solid #0086e9;
    }
    .userMsg-password-box .el-dialog__header{
        height: 50px;
        line-height: 50px;
        padding: 0 20px;
        border-bottom: 1px solid #cbcbcb;
    }
    .userMsg-password-box .el-dialog__title{
        font-weight: normal;
        color: #2b2b2b;
    }
    .userMsg-password-box .el-form-item__label{
        color: #666666;
        padding: 8px 12px;
    }
    .userMsg-password-box .el-form-item__content{
        line-height: 30px;
    }
    .userMsg-password-box .el-form-item + .el-form-item{
        margin: 20px 0 0 0;
    }
    .userMsg-password-box .el-dialog__footer{
        padding: 0;
        text-align: center;
    }
    .userMsg-password-box .el-button--primary{
        width: 120px;
        height: 30px;
        padding: 6px 0;
    }
</style>
