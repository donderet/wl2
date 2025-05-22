package ua.kpi.service;

import ua.kpi.model.Pizza;

import java.util.List;

public interface PizzaService {
    void createPizza(Pizza pizza);
    Pizza getPizzaById(int id);
    List<Pizza> getAllPizzas();
    void updatePizza(Pizza pizza);
    void deletePizza(int id);
} 