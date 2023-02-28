package model.modelDAO.groupDAO;

import model.entities.Group;
import model.modelDAO.AbstractDAO;

public class GroupDAOImpl extends AbstractDAO<Group> implements GroupDAO {
    public GroupDAOImpl(){
        super(Group.class);
    }
}
