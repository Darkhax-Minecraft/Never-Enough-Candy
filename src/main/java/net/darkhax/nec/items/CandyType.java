package net.darkhax.nec.items;

import net.minecraft.entity.EntityLiving;

public class CandyType {
    
    public String name;
    public int food;
    public float odds;
    public Class entityType;
    public boolean doesWolfLike;
    public ItemCandy item;
    
    public CandyType(String name, int food, float odds, Class<? extends EntityLiving> entityType) {
        
        this(name, food, odds, entityType, false);
    }
    
    public CandyType(String name, int food, float odds, Class<? extends EntityLiving> entityType, boolean doesWolfLike) {
        
        this.name = name;
        this.food = food;
        this.odds = odds;
        this.entityType = entityType;
        this.doesWolfLike = doesWolfLike;
    }
}