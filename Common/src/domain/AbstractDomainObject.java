/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author Yo
 */
public interface AbstractDomainObject extends Serializable
{
    public String getTableName();
    public String getAlias();
    public String getJoinCondition();
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
    public String getInsertColumns();
    
    public String getInsertPlaceholders();
    public String getUpdatePlaceholders();
    public String getConditionPlaceholder(); 
    public String getSelectConditionPlaceholder();
    public String getFilterConditionPlaceholder();
    public String getExistenceConditionPlaceholder();
    
    void prepareInsert(PreparedStatement ps) throws SQLException;
    void prepareUpdate(PreparedStatement ps) throws SQLException;
    void prepareCondition(PreparedStatement ps) throws SQLException;
    void prepareSelect(PreparedStatement ps) throws SQLException;
    void prepareFilter(PreparedStatement ps) throws SQLException;
    public void prepareExistenceCondition(PreparedStatement ps) throws SQLException;
}
