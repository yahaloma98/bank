package org.example.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCDao {
    /**
     *  * 增加，删除，修改
     */
    public int insertOrDeleteOrUpdate(String sql) {
        int res = -1;
        try {
            Connection connection = DBHelper.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(sql);
            int execute = pst.executeUpdate();
            System.out.println("执行语句：" + sql + "," + execute + "行数据受影响");
            connection.commit();
            DBHelper.closeConnection(null, connection,pst);
            res = 1;
        } catch (SQLException e) {
            //System.out.println("异常提醒：" + e);
        }

        return res;
    }
    /**
     *  * 查询，返回结果集
     */
    public List<Map<String, Object>> select(String sql) {
        List<Map<String, Object>> returnResultToList = null;
        try {
            Connection connection = DBHelper.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            returnResultToList = returnResultToList(resultSet);
            connection.commit();
            DBHelper.closeConnection(resultSet, connection,pst );
        } catch (SQLException e) {
            //System.out.println("异常提醒：" + e);
        }
        return returnResultToList;
    }
    /**
     *  * 数据返回集合  * @param resultSet  * @return  * @throws SQLException
     */
    public List<Map<String, Object>> returnResultToList(ResultSet resultSet) {
        List<Map<String, Object>> values = null;
        try {
            values = new ArrayList<Map<String, Object>>();
            List<String> columnName = new ArrayList<String>();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                columnName.add(rsmd.getColumnLabel(i + 1));
            }
            Map<String, Object> map = null;
            while (resultSet.next()) {
                map = new HashMap<String, Object>();
                for (String column : columnName) {
                    Object value = resultSet.getObject(column);
                    map.put(column, value);
                }
                // 把一条记录的 Map 对象放入准备的 List 中
                values.add(map);
                //System.out.println();
            }
        } catch (SQLException e) {
            //System.out.println("异常提醒：" + e);
        }
        return values;
    }
}
