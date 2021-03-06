package cn.citynight.service.impl;

import cn.citynight.dao.IAccountDao;
import cn.citynight.domain.Account;
import cn.citynight.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author logan
 * @date 2020/9/7 10:27 下午
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//只读型事务的配置
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;
    public Account findAccountById(Integer accountId) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer");

        // 根据账户名查询转出账户
        Account source = accountDao.findAccountByName(sourceName);

        // 根据账户名查询转入账户
        Account target = accountDao.findAccountByName(targetName);

        // 转出账户减钱
        source.setMoney(source.getMoney() - money);
        // 转入账户加钱
        target.setMoney(target.getMoney() + money);

        // 更新转出账户
        accountDao.updateAccount(source);
        // 更新转入账户
        accountDao.updateAccount(target);
    }
}
