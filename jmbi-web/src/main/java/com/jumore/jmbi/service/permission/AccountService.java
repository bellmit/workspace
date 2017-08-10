package com.jumore.jmbi.service.permission;

import com.jumore.dove.service.BaseService;
import com.jumore.jmbi.common.exceptions.BizException;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.model.param.PageQueryParam;
import com.jumore.jmbi.model.param.permission.AccountQueryParam;
import com.jumore.jmbi.model.param.permission.PasswordParam;
import com.jumore.jmbi.model.vo.PageResult;

/**
 *
 * @author:zhuwei
 * @since: 2016年7月1日 上午11:31:34
 * @history:
 */
public interface AccountService extends BaseService {

    /**
     * 修改用户密码
     *
     * @param param 前台参数
     * @param isCheckPwd 是否校验原始密码，true：校验，false：不校验
     * @return
     * @history
     */
    public boolean updatePassword(PasswordParam param, boolean isCheckPwd) throws BizException;

    /**
     * 列表查询
     * 
     * @param queryParam
     * @return
     * @history:
     */
    PageResult<Account> queryPage(AccountQueryParam queryParam);

    /**
     * 新增
     * 
     * @param account
     * @throws BizException
     * @history:
     */
    void add(Account account) throws BizException;

    /**
     * 重置密码
     * 
     * @param id 被重置密码账户的：id
     * @return
     * @throws BizException
     * @history:
     */
    boolean reSetPassword(Integer id) throws BizException;

    /**
     * 功能说明：获取分页参数：：按照bootstrap table的{起始行数、每页数量}进行分页
     * 
     * @param entityType
     * @param pageQueryParam
     * @return Page<T>
     */
    <T> PageResult<T> getPageResult(Class<T> entityType, PageQueryParam pageQueryParam, String statement);

}
