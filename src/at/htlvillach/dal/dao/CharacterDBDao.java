package at.htlvillach.dal.dao;

import at.htlvillach.bll.Character;
import at.htlvillach.dal.DatabaseManager;

import java.util.List;

public class CharacterDBDao implements Dao<Character> {

    @Override
    public List<Character> getAll() {
        return DatabaseManager.getInstance().getAllCharacters();
    }

    @Override
    public Character getById(int id) {
        return DatabaseManager.getInstance().getCharacterById(id);
    }

    @Override
    public boolean insert(Character item) {
        return DatabaseManager.getInstance().insertCharacter(item);
    }

    @Override
    public boolean delete(Character item) {
        return DatabaseManager.getInstance().deleteCharacter(item);
    }

    @Override
    public boolean update(Character item) {
        return DatabaseManager.getInstance().updateCharacter(item);
    }
}
