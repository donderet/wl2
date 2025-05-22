package ua.kpi.service.impl;

import ua.kpi.model.Pizza;
import ua.kpi.model.dao.PizzaDAO;
import ua.kpi.model.dao.DaoFactory;
import ua.kpi.service.PizzaService;

import java.util.List;

public class PizzaServiceImpl implements PizzaService {

    private DaoFactory daoFactory = DaoFactory.getInstance();
    private PizzaDAO pizzaDAO = daoFactory.createPizzaDAO();

    @Override
    public void createPizza(Pizza pizza) {
        pizzaDAO.create(pizza);
    }

    @Override
    public Pizza getPizzaById(int id) {
        return pizzaDAO.findById(id);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaDAO.findAll();
    }

    @Override
    public void updatePizza(Pizza pizza) {
        pizzaDAO.update(pizza);
    }

    @Override
    public void deletePizza(int id) {
        pizzaDAO.delete(id);
    }
} 