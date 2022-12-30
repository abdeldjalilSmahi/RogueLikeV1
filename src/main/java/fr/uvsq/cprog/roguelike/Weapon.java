package fr.uvsq.cprog.roguelike;

public class Weapon extends WorldObject{
    private TypeWeapon type;

    public Weapon(int x, int y, TypeWeapon type) {
        super(x, y, type.getAsciiChar());
        this.type = type;
    }

    public TypeWeapon getType() {
        return type;
    }

    public void setType(TypeWeapon type) {
        this.type = type;
        setAsciiChar(type.getAsciiChar());
    }
    public int getWeaponDamage(){
        return this.type.getDamage() ;
    }
}
