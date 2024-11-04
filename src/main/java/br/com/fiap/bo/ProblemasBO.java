package br.com.fiap.bo;

import br.com.fiap.dao.ProblemasDAO;
import br.com.fiap.to.ProblemasTO;

import java.util.ArrayList;

public class ProblemasBO{
    private ProblemasDAO problemasDAO;

    public ArrayList<ProblemasTO> findAll() {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.findAll();
    }

    public ProblemasTO findByCodigo(Long codigo) {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.findByCodigo(codigo);
    }

    public ProblemasTO save(ProblemasTO problemas) {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.save(problemas);
    }

    public boolean delete(Long codigo) {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.delete(codigo);
    }
    public ProblemasTO update(ProblemasTO problemas) {
        problemasDAO = new ProblemasDAO();
        return problemasDAO.update(problemas);
    }
}