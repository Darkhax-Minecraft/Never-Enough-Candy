package net.darkhax.nec.items;

public class CandyType {
    
    public String name;
    public String type;
    public int food;
    public float odds;
    public boolean doesWolfLike;
    public ItemCandy item;
    
    public CandyType(String name, int food, float odds, String type) {
        
        this(name, food, odds, type, false);
    }
    
    public CandyType(String name, int food, float odds, String type, boolean doesWolfLike) {
        
        this.name = name;
        this.food = food;
        this.odds = odds;
        this.type = type;
        this.doesWolfLike = doesWolfLike;
    }
}