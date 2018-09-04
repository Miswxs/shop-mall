package com.lj.db.inteceptor;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.lj.common.util.ReflectUtil;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xiaoshen.wang on 2018/7/9
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})
})
public class UpdatePlugin implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(UpdatePlugin.class);
    private static final String ADD = "ADD";
    private static final String UPDATE = "UPDATE";
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static HashMap<String,List<AutoCompleteColumn>> completeColumnMap = new HashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if(invocation.getTarget() instanceof RoutingStatementHandler){
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(statementHandler, "delegate");
            BoundSql boundSql = delegate.getBoundSql();
            try {
                SQLStatement sqlStatement = new MySqlStatementParser(boundSql.getSql()).parseStatement();
                if(sqlStatement instanceof MySqlInsertStatement){
                    buildInsertSql((MySqlInsertStatement)sqlStatement);
                    ReflectUtil.setFieldValue(boundSql, "sql", SQLUtils.toMySqlString(sqlStatement));
                }else if(sqlStatement instanceof MySqlUpdateStatement){
                    buildUpdateSql((MySqlUpdateStatement) sqlStatement);
                    ReflectUtil.setFieldValue(boundSql, "sql", SQLUtils.toMySqlString(sqlStatement));
                }else{
                    return invocation.proceed();
                }
            }catch (Exception e){
                logger.error("error sql "+boundSql.getSql()+"{}",e);
            }

        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private String getLoginUserAccount(){
        /*String account = ThreadLocalUtil.currentAccount();
        logger.info("当前登录信息人:{}", account);
        return StringUtils.isEmpty(account) ? "remote" : account;*/
        return "system";
    }


    private void buildUpdateSql(MySqlUpdateStatement updateStatement){
        if(updateStatement.getTableSource() instanceof SQLExprTableSource) {
            List<AutoCompleteColumn> updateCompleteColumnList = getAutoColumnList(UPDATE);
            updateCompleteColumnList = removeContainItem(updateStatement.getItems(), updateCompleteColumnList);
            SQLUpdateSetItem updateSetItem = null;
            for (AutoCompleteColumn autoCompleteColumn : updateCompleteColumnList) {
                updateSetItem = new SQLUpdateSetItem();
                updateSetItem.setColumn(buildSQLIdExpr(autoCompleteColumn.getColumn()));
                updateSetItem.setValue(buildValueExpr(UPDATE, autoCompleteColumn.getValueType()));
                updateSetItem.setParent(updateStatement);
                updateStatement.getItems().add(updateSetItem);
            }
        }
    }

    private void buildInsertSql(MySqlInsertStatement insertStatement){
        List<AutoCompleteColumn> addCompleteColumnList = getAutoColumnList(ADD);
        addCompleteColumnList = removeContainColumn(insertStatement.getColumns(),addCompleteColumnList);
        for (AutoCompleteColumn autoCompleteColumn : addCompleteColumnList) {
            insertStatement.getColumns().add(buildSQLIdExpr(autoCompleteColumn.getColumn()));
            for (SQLInsertStatement.ValuesClause vc : insertStatement.getValuesList()) {
                vc.addValue(buildValueExpr(ADD, autoCompleteColumn.getValueType()));
            }
        }
    }

    private List<AutoCompleteColumn> removeContainColumn(List<SQLExpr> exprList, List<AutoCompleteColumn> completeColumns){
        for (int i = 0; i < completeColumns.size(); i++) {
            AutoCompleteColumn completeColumn = completeColumns.get(i);
            for (SQLExpr expr : exprList) {
                if (((SQLIdentifierExpr) expr).getName().toLowerCase().equals(completeColumn.getColumn().toLowerCase())) {
                    completeColumns.remove(i);
                    i--;
                    continue;
                }
            }
        }
        return completeColumns;
    }

    private List<AutoCompleteColumn> removeContainItem(List<SQLUpdateSetItem> itemList,List<AutoCompleteColumn> completeColumns){
        for (int i = 0; i < completeColumns.size(); i++) {
            AutoCompleteColumn completeColumn = completeColumns.get(i);
            for (SQLUpdateSetItem item : itemList) {
                if(((SQLUpdateSetItem)item).getColumn() instanceof SQLPropertyExpr){
                    if(((SQLPropertyExpr)((SQLUpdateSetItem)item).getColumn()).getName().toLowerCase().equals(completeColumn.getColumn().toLowerCase())){
                        completeColumns.remove(i);
                        i--;
                        continue;
                    }
                }else {
                    if(((SQLIdentifierExpr)((SQLUpdateSetItem)item).getColumn()).getName().toLowerCase().equals(completeColumn.getColumn().toLowerCase())){
                        completeColumns.remove(i);
                        i--;
                        continue;
                    }
                }

            }
        }
        return completeColumns;
    }

    private SQLExpr buildValueExpr(String type,ValueTypeEnum valueType){
        switch (valueType){
            case DATE_TIME_NOW:
                return  new SQLCharExpr(sdf.format(new Date()));
            case USER_ACCOUNT:
                return  new SQLCharExpr(getLoginUserAccount());
            /*case VERSION:
                if(type.equals(UPDATE)){
                    return new SQLBinaryOpExpr(buildSQLIdExpr("version"), SQLBinaryOperator.Add, new SQLIntegerExpr(1));
                }else if(type.equals(ADD)){
                    return new SQLIntegerExpr(0);
                }*/
            default:
                logger.error("错误的值类型");
                return null;
        }
    }
    private SQLExpr buildSQLIdExpr(String val){
        return new SQLIdentifierExpr(val);
    }
    private List<AutoCompleteColumn> getAutoColumnList(String key){
        if(completeColumnMap.get(key) == null){
            AutoCompleteColumn createBy =  new AutoCompleteColumn("created_by", ValueTypeEnum.USER_ACCOUNT);
            AutoCompleteColumn createdAt =  new AutoCompleteColumn("created_at", ValueTypeEnum.DATE_TIME_NOW);
            AutoCompleteColumn updatedBy =  new AutoCompleteColumn("updated_by", ValueTypeEnum.USER_ACCOUNT);
            AutoCompleteColumn updatedAt =  new AutoCompleteColumn("updated_at", ValueTypeEnum.DATE_TIME_NOW);
            //AutoCompleteColumn version =  new AutoCompleteColumn("version", ValueTypeEnum.VERSION);
            List<AutoCompleteColumn> list = new ArrayList<>();
            list.add(updatedBy);
            list.add(updatedAt);
            //list.add(version);
            if(ADD.equals(key)){
                list.add(createBy);
                list.add(createdAt);
            }
            completeColumnMap.put(key,list);
            return  new ArrayList<>(list);
        }else{
            //返回新对象
            return new ArrayList<>(completeColumnMap.get(key));
        }
    }

    static class AutoCompleteColumn{
        private String column;//字段名称
        private ValueTypeEnum valueType;//值类型

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public ValueTypeEnum getValueType() {
            return valueType;
        }

        public void setValueType(ValueTypeEnum valueType) {
            this.valueType = valueType;
        }

        public AutoCompleteColumn(String column, ValueTypeEnum valueType) {
            this.column = column;
            this.valueType = valueType;
        }
    }

    enum ValueTypeEnum{
        DATE_TIME_NOW,USER_ACCOUNT
    }

}
