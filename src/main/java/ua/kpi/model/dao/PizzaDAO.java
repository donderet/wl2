package ua.kpi.model.dao;

import ua.kpi.model.Pizza;

import java.util.List;

public interface PizzaDAO {
    void create(Pizza pizza);
    Pizza findById(int id);
    List<Pizza> findAll();
    void update(Pizza pizza);
    void delete(int id);
} 