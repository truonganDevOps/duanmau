/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface DAO {
    public abstract void insert(Object... args);
    public abstract void delete(Object... args);
    public abstract void update(Object... args);
    public abstract void select(Object... args);
    public abstract ResultSet readFromRS(ResultSet rs);
}
